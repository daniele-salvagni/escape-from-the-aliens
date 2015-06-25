package it.polimi.ingsw.cg_2.controller;

import it.polimi.ingsw.cg_2.messages.Token;
import it.polimi.ingsw.cg_2.messages.requests.ConnectionRequest;
import it.polimi.ingsw.cg_2.messages.requests.RequestMsg;
import it.polimi.ingsw.cg_2.messages.responses.ConnectionResponseMsg;
import it.polimi.ingsw.cg_2.messages.responses.InvalidRequestMsg;
import it.polimi.ingsw.cg_2.messages.responses.ResponseMsg;
import it.polimi.ingsw.cg_2.view.commons.RequestHandler;
import it.polimi.ingsw.cg_2.view.gamemanager.PublisherInterface;

import java.rmi.RemoteException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This controller controls multiple games, implements a RequestHandler to receive
 * clients
 * requests, process them and send back responses. It acts also as a WaitingRoom for new
 * players connecting to the game manager. When there are two players a countdown starts
 * to start a new game, if in the meantime someone else tries to connect the timer does
 * reset and start over. If the number of waiting players reaches 8 the game starts
 * instantly. The first player that connected to the waiting room is free to change the
 * default Zone where to play.
 */
public class GamesController implements RequestHandler {

    private static final Logger LOG = Logger.getLogger(GamesController.class.getName());

    private static final int COUNTDOWN = 10;

    private final PublisherInterface publisherInterface;
    private final Map<Token, GameController> clients;

    Timer timer;
    TimerTask timerTask;

    private final List<Token> waitingPlayers;
    private GameController waitingGame;

    /**
     * Create a new GamesController.
     *
     * @param publisher the publisher interface to notify clients
     */
    public GamesController(PublisherInterface publisher) {

        timer = new Timer();
        waitingPlayers = new ArrayList<>();
        this.publisherInterface = publisher;
        clients = new HashMap<>();
        waitingGame = new GameController(publisher);

    }

    /**
     * Handle a new client trying to join a game. When there are two players a countdown
     * starts to start a new game, if in the meantime someone else tries to connect the
     * timer does reset and start over. If the number of waiting players reaches 8 the
     * game starts instantly.
     *
     * @param token the token of the client trying to join a new game
     */
    public void newPlayer(Token token) {

        waitingPlayers.add(token);
        waitingGame.addPlayer(token);
        clients.put(token, waitingGame);

        LOG.log(Level.INFO, "New player added to waiting room.");

        if ((waitingPlayers.size() >= 2) && (waitingPlayers.size() <= 7)) {

            timer.cancel();

            timerTask = new TimerTask() {
                @Override
                public void run() {
                    try {

                        waitingGame.initGame();
                        waitingGame = new GameController(publisherInterface);
                        waitingPlayers.clear();

                        LOG.log(Level.INFO, "New game created.");

                    } catch (Exception e) {
                        LOG.log(Level.SEVERE, "Unexpected exception while creating a " +
                                "new game.", e);
                    }
                }
            };

            timer = new Timer();

            timer.schedule(timerTask, (long) COUNTDOWN * 1000);

        } else if ((waitingPlayers.size() == 8)) {

            timerTask.cancel();
            timer.cancel();
            // Start game instantly
            timer.schedule(timerTask, 0);

        }

    }

    @Override
    public Token connect() throws RemoteException {

        Token newToken = new Token(UUID.randomUUID().toString());
        newPlayer(newToken);
        return newToken;

    }

    @Override
    public ResponseMsg processRequest(RequestMsg request) throws RemoteException {

        if (request instanceof ConnectionRequest) {

            ConnectionRequest connectionRequest = (ConnectionRequest) request;
            if (connectionRequest.getToken() == null) {
                return new ConnectionResponseMsg(connect());
            } else {
                return new InvalidRequestMsg("Reconnection is not supported yet.");
            }

        } else {

            Token requestToken = request.getToken();

            if (!clients.containsKey(requestToken)) {
                return new InvalidRequestMsg("Invalid Token.");
            } else {

                GameController game = clients.get(requestToken);
                return game.handleRequest(request);

            }

        }

    }

}
