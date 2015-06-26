package it.polimi.ingsw.cg_2.controller;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.controller.actions.ActionFactoryVisitor;
import it.polimi.ingsw.cg_2.controller.actions.ActionFactoryVisitorImpl;
import it.polimi.ingsw.cg_2.controller.turn.FinishedState;
import it.polimi.ingsw.cg_2.controller.turn.TurnMachine;
import it.polimi.ingsw.cg_2.messages.ResultMsgPair;
import it.polimi.ingsw.cg_2.messages.Token;
import it.polimi.ingsw.cg_2.messages.broadcast.BroadcastMsg;
import it.polimi.ingsw.cg_2.messages.broadcast.ChatBroadcastMsg;
import it.polimi.ingsw.cg_2.messages.broadcast.GameStartedBroadcastMsg;
import it.polimi.ingsw.cg_2.messages.broadcast.UseTlpItemBroadcastMsg;
import it.polimi.ingsw.cg_2.messages.requests.ChangeMapRequestMsg;
import it.polimi.ingsw.cg_2.messages.requests.PrivateDataRequestMsg;
import it.polimi.ingsw.cg_2.messages.requests.RequestMsg;
import it.polimi.ingsw.cg_2.messages.requests.actions.ActionRequestMsg;
import it.polimi.ingsw.cg_2.messages.requests.actions.SendChatMsg;
import it.polimi.ingsw.cg_2.messages.responses.AckResponseMsg;
import it.polimi.ingsw.cg_2.messages.responses.InvalidRequestMsg;
import it.polimi.ingsw.cg_2.messages.responses.PrivateDataResponseMsg;
import it.polimi.ingsw.cg_2.messages.responses.ResponseMsg;
import it.polimi.ingsw.cg_2.model.Game;
import it.polimi.ingsw.cg_2.model.deck.DecksFactory;
import it.polimi.ingsw.cg_2.model.deck.ItemCard;
import it.polimi.ingsw.cg_2.model.deck.StandardDecksFactory;
import it.polimi.ingsw.cg_2.model.map.ZoneFactory;
import it.polimi.ingsw.cg_2.model.map.ZoneName;
import it.polimi.ingsw.cg_2.model.player.CharacterRace;
import it.polimi.ingsw.cg_2.model.player.Player;
import it.polimi.ingsw.cg_2.model.player.PlayersFactory;
import it.polimi.ingsw.cg_2.model.player.StandardPlayersFactory;
import it.polimi.ingsw.cg_2.utils.exception.InvalidMsgException;
import it.polimi.ingsw.cg_2.view.gamemanager.PublisherInterface;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The controller for a single game, it manages the requests obtained by the GamesManager
 * (that dispatches messages to the correct game) and manages the execution of actions by
 * using a TurnMachine.
 */
public class GameController {

    private static final Logger LOG = Logger.getLogger(GameController.class
            .getName());

    private static final int MAX_TURNS = 39;

    private static int numberOfGames = 1;
    private final int gameID;

    private Game game;
    private TurnMachine turnMachine;
    private ActionFactoryVisitor actionFactory;

    private final List<Token> players;

    private ZoneName zone;

    private final PublisherInterface publisherInterface;

    private final List<BroadcastMsg> publicLog;

    /**
     * Create a new gameController, the game is initially instantiated with a standard
     * decks factory, a standard players factory and the map Galilei (can be changed by
     * clients).
     *
     * @param publisherInterface the publisher interface to notify subscribed clients
     */
    public GameController(PublisherInterface publisherInterface) {

        // Make a copy, minimize mutability
        this.players = new ArrayList<>();
        this.publisherInterface = publisherInterface;

        // This is just the default map, can be changed
        zone = ZoneName.GALILEI;

        gameID = numberOfGames;
        numberOfGames++;

        // The log of executed public actions
        publicLog = new ArrayList<>();

        // Create a new GAMEx topic.
        publisherInterface.addTopic(getTopic());

    }

    /**
     * Change the map (zone) where the game will be played, can be done only before
     * starting the game.
     *
     * @param request the map change request message
     * @return the appropriate response message
     */
    public ResponseMsg changeMap(ChangeMapRequestMsg request) {

        // Only if the game did not already started
        if (game == null) {

            if (!(players.indexOf(request.getToken()) == 0)) {
                return new InvalidRequestMsg("Only the first player can change map.");
            }

            try {
                zone = ZoneName.valueOf(request.getMap());
                return new AckResponseMsg("Map changed to " + request.getMap());
            } catch (Exception e) {
                LOG.log(Level.WARNING, "There was a problem changing the map.");
            }
        }

        return new InvalidRequestMsg("Can't change map.");

    }

    /**
     * Add a player to the game (only if the game did not already started).
     *
     * @param token the token of the player to add
     */
    public void addPlayer(Token token) {

        if (game == null) {
            players.add(token);
            // Subscribe the client to the topic of this game
            publisherInterface.subscribeClientToTopic(getTopic(), token);
        }

    }

    /**
     * Initialize the game model and start the turn machine.
     */
    public void initGame() {

        ZoneFactory zFactory = ZoneFactory.newLoader(zone);
        DecksFactory dFactory = new StandardDecksFactory();
        PlayersFactory pFactory = new StandardPlayersFactory();

        game = Game.initialize(zFactory, dFactory, pFactory, players.size());
        turnMachine = new TurnMachine(game);
        actionFactory = new ActionFactoryVisitorImpl(game, players, turnMachine);

        // TODO: Remove
        publisherInterface.publish(new GameStartedBroadcastMsg(getGameID(), players
                .size(), zone.name(), "ADVANCED", 0, null), getTopic());

    }

    /**
     * Get the progressive ID of this game.
     *
     * @return the ID of this game
     */
    public int getGameID() {

        return gameID;

    }

    /**
     * Get the topic of this game for publisher-subscriber communication.
     *
     * @return the main topic of this game
     */
    public String getTopic() {

        return "GAME" + Integer.toString(gameID);

    }

    /**
     * Check if the game is finished.
     *
     * @return true, if the game is finished
     */
    private boolean isGameFinished() {

        // Last turn played
        if (game.getTurnNumber() > MAX_TURNS) {
            return true;
        }

        List<Player> gamePlayers = game.getPlayers();
        List<Player> playingAliens = new ArrayList<>();
        List<Player> playingHumans = new ArrayList<>();

        for (Player player : gamePlayers) {
            if (player.getCharacter().getRace() == CharacterRace.ALIEN) {
                if (!player.isSuspended() && player.getCharacter().isAlive()) {
                    playingAliens.add(player);
                }
            } else { // HUMAN
                if (!player.isSuspended() && player.getCharacter().isAlive() && !player
                        .getCharacter().isEscaped()) {
                    playingHumans.add(player);
                }
            }
        }

        // All the humans escaped or died, or all the aliens died
        return playingAliens.isEmpty() || playingHumans.isEmpty();

    }

    private void finishGame() {

        //publisherInterface.publish();

        turnMachine.setState(FinishedState.getInstance());

    }

    /**
     * Check if it is the turn of the player with the given token
     *
     * @param token the token of the player to check
     * @return true, if it is his turn
     */
    private boolean isPlayerTurn(Token token) {

        return players.indexOf(token) == game.getCurrentPlayerNumber();

    }

    /**
     * Handle a request from the client, a Message is passed to a Factory that
     * uses the Visitor pattern to create an appropriate atomic Action (command
     * pattern), then the action is passed to a FSM (state pattern) to be
     * executed.
     *
     * @param request the request message from the client
     * @return the result of the execution of the message (response & broadcast)
     */
    private ResultMsgPair executeAction(ActionRequestMsg request) {

        Action action;

        try {

            // Create the appropriate action using the Visitor Pattern
            action = request.createAction(actionFactory);

            // Execute the action and return a result message pair (private
            // response + public broadcast)
            return turnMachine.executeAction(action);

        } catch (InvalidMsgException e) {

            LOG.log(Level.WARNING, "Handled an invalid request.", e);
            // Notify the client that the message is not valid
            return new ResultMsgPair(new InvalidRequestMsg("Invalid request content."),
                    null);

        }

    }

    /**
     * Fetch the private data of a single player upon request.
     *
     * @param request the request message
     * @return the appropriate response containing player's private data
     */
    private ResponseMsg fetchPrivateData(PrivateDataRequestMsg request) {

        if (game == null) {
            return new InvalidRequestMsg("The game is not started yet.");
        }

        int playerNumber = players.indexOf(request.getToken());
        Player gamePlayer = game.getPlayers().get(playerNumber);

        String pRankStr = gamePlayer.getCharacter().getRank().name();
        String pRaceStr = gamePlayer.getCharacter().getRace().name();

        List<String> heldItems = new ArrayList<>();
        List<String> activatedItems = new ArrayList<>();

        String position = gamePlayer.getCharacter().getPosition().getCooridnate()
                .getOddQCol() + ":" + gamePlayer.getCharacter().getPosition()
                .getCooridnate().getOddQRow();

        for (ItemCard heldItem : gamePlayer.getHeldItems()) {

            heldItems.add(heldItem.getType().name());

        }

        for (ItemCard.ItemCardType activeItem : gamePlayer.getActiveItems()) {

            activatedItems.add(activeItem.name());

        }

        return new PrivateDataResponseMsg(pRaceStr, pRankStr, playerNumber, position,
                heldItems, activatedItems);

    }

    /**
     * Handles a chat message by sending an acknowledgment to th sender and broadcasting
     * it to the other players of the game.
     *
     * @param request the chat request
     * @return the acknowledgment
     */
    private ResponseMsg handleChatMessage(SendChatMsg request) {

        int clientNum = players.indexOf(request.getToken());
        BroadcastMsg broadcast = new ChatBroadcastMsg(clientNum, request.getMessage());
        publisherInterface.publish(broadcast, getTopic());
        return new AckResponseMsg("Chat message delivered.");

    }

    /**
     * Handle a generic request coming from a client, and generate a response to be sent
     * back to the player and, if necessary, broadcast a message to all the clients
     * subscribed to a certain topic.
     *
     * @param request the request
     * @return the appropriate response
     */
    public ResponseMsg handleRequest(RequestMsg request) {

        // ########################################### Requests that can always be handled

        if (request instanceof SendChatMsg) {
            return handleChatMessage((SendChatMsg) request);
        }

        if (request instanceof ChangeMapRequestMsg) {
            return changeMap((ChangeMapRequestMsg) request);
        }

        // ################################## Requests that require the game to be started

        if (game == null) {
            return new InvalidRequestMsg("Please wait for the game to start.");
        }

        if (request instanceof PrivateDataRequestMsg) {
            return fetchPrivateData((PrivateDataRequestMsg) request);
        }

        // ############################# Requests that require the player playing the turn

        if (!isPlayerTurn(request.getToken())) {
            return new InvalidRequestMsg("Please wait your turn to play.");
        }

        if (request instanceof ActionRequestMsg) {

            ResultMsgPair result = executeAction((ActionRequestMsg) request);

            if (result.broadcast != null) {
                // Publish a public message
                publisherInterface.publish(result.broadcast, getTopic());
                // Add it to the public log
                publicLog.add(result.broadcast);
            }

            return result.response;

        }

        // BTW this Should never be reached
        return null;

    }


}
