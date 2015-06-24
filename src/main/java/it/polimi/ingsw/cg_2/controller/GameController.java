package it.polimi.ingsw.cg_2.controller;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.controller.actions.ActionFactoryVisitor;
import it.polimi.ingsw.cg_2.controller.turn.TurnMachine;
import it.polimi.ingsw.cg_2.messages.ResultMsgPair;
import it.polimi.ingsw.cg_2.messages.Token;
import it.polimi.ingsw.cg_2.messages.requests.RequestMsg;
import it.polimi.ingsw.cg_2.messages.requests.actions.ActionRequestMsg;
import it.polimi.ingsw.cg_2.messages.responses.InvalidRequestMsg;
import it.polimi.ingsw.cg_2.messages.responses.ResponseMsg;
import it.polimi.ingsw.cg_2.model.Game;
import it.polimi.ingsw.cg_2.model.deck.DecksFactory;
import it.polimi.ingsw.cg_2.model.deck.StandardDecksFactory;
import it.polimi.ingsw.cg_2.model.map.ZoneFactory;
import it.polimi.ingsw.cg_2.model.map.ZoneLoader;
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
 *
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

        // Create a new GAMEX topic and automatically subscribe the clients of this game.
        publisherInterface.addTopic(getTopic(), new HashSet<>(players));

    }

    public void initGame() {

        game = Game.initialize(zFactory, dFactory, pFactory, players.size());
        turnMachine = new TurnMachine(game);

    }

    public int getGameID() {

        return gameID;

    }

    public String getTopic() {

        return "GAME" + Integer.toString(gameID);

    }

    /**
     * Check if the game is finished.
     *
     * @return true, if the game is finished
     */
    private boolean isGameFinished() {

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

        if (playingAliens.isEmpty() || playingHumans.isEmpty()) {
            return true;
        }

        return false;

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
    public ResultMsgPair executeAction(ActionRequestMsg request) {

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

    private boolean isPlayerTurn(Token token) {

        return players.indexOf(token) == game.getCurrentPlayerNumber();

    }

    public ResponseMsg handleRequest(RequestMsg request) {

        if (game == null) {
            return new InvalidRequestMsg("Please wait for the game to start.");
        }

        if (!isPlayerTurn(request.getToken())) {
            return new InvalidRequestMsg("Please wait your turn to play.");
        }

        if (request instanceof ActionRequestMsg) {

            ResultMsgPair result = executeAction((ActionRequestMsg) request);

            if (result.broadcast != null) {
                publisherInterface.publish(result.broadcast, getTopic());
            }

            return result.response;

        }

        // TODO: Other Requests (not actions, if any)
        return null;

    }


}
