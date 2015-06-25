package it.polimi.ingsw.cg_2.controller;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.controller.actions.ActionFactoryVisitor;
import it.polimi.ingsw.cg_2.controller.turn.TurnMachine;
import it.polimi.ingsw.cg_2.messages.ResultMsgPair;
import it.polimi.ingsw.cg_2.messages.Token;
import it.polimi.ingsw.cg_2.messages.broadcast.BroadcastMsg;
import it.polimi.ingsw.cg_2.messages.broadcast.ChatBroadcastMsg;
import it.polimi.ingsw.cg_2.messages.broadcast.UseTlpItemBroadcastMsg;
import it.polimi.ingsw.cg_2.messages.requests.RequestMsg;
import it.polimi.ingsw.cg_2.messages.requests.actions.ActionRequestMsg;
import it.polimi.ingsw.cg_2.messages.requests.actions.SendChatMsg;
import it.polimi.ingsw.cg_2.messages.responses.AckResponseMsg;
import it.polimi.ingsw.cg_2.messages.responses.InvalidRequestMsg;
import it.polimi.ingsw.cg_2.messages.responses.ResponseMsg;
import it.polimi.ingsw.cg_2.model.Game;
import it.polimi.ingsw.cg_2.model.deck.DecksFactory;
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

    private DecksFactory dFactory;
    private PlayersFactory pFactory;
    private ZoneFactory zFactory;

    private final PublisherInterface publisherInterface;

    private final List<BroadcastMsg> publicLog;

    /**
     * Create a new gameController, the game is initially instantiated with a standard
     * decks factory, a standard players factory and the map Galilei (can be changed by
     * clients).
     *
     * @param players            the token of the clients of this game
     * @param publisherInterface the publisher interface to notify subscribed clients
     */
    public GameController(List<Token> players, PublisherInterface publisherInterface) {

        // Make a copy, minimize mutability
        this.players = new ArrayList<>(players);
        this.publisherInterface = publisherInterface;

        dFactory = new StandardDecksFactory();
        pFactory = new StandardPlayersFactory();

        // This is just the default map, can be changed
        zFactory = ZoneFactory.newLoader(ZoneName.GALILEI);

        gameID = numberOfGames;
        numberOfGames++;

        // The log of executed public actions
        publicLog = new ArrayList<>();

        // Create a new GAMEX topic and automatically subscribe the clients of this game.
        publisherInterface.addTopic(getTopic(), new HashSet<>(players));
        publisherInterface.publish(new UseTlpItemBroadcastMsg(1, "3:7"), getTopic());

    }

    /**
     * Initialize the game model and start the turn machine.
     */
    public void initGame() {

        game = Game.initialize(zFactory, dFactory, pFactory, players.size());
        turnMachine = new TurnMachine(game);

    }

    /**
     * Get the progressive ID of this game
     *
     * @return the ID of this game
     */
    public int getGameID() {

        return gameID;

    }

    /**
     * Get the topic of this game for publisher-subscriber communication
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
     * Check if it is the turn of the player with the given token
     *
     * @param token the token of the player to check
     * @return true, if it is his turn
     */
    private boolean isPlayerTurn(Token token) {

        return players.indexOf(token) == game.getCurrentPlayerNumber();

    }

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
     * @param request
     * @return
     */
    public ResponseMsg handleRequest(RequestMsg request) {

        // ########################################### Requests that can always be handled

        if (request instanceof SendChatMsg) {
            handleChatMessage((SendChatMsg) request);
        }

        // ################################## Requests that require the game to be started

        if (game == null) {
            return new InvalidRequestMsg("Please wait for the game to start.");
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

        // BTW this Should not be reached
        return null;

    }


}
