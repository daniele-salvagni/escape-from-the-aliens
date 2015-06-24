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

/**
 *
 */
public class GamesController implements RequestHandler {

    private static final int COUNTDOWN = 30;

    private PublisherInterface publisherInterface;
    private Map<Token, GameController> clients;

    Timer timer;
    TimerTask timerTask;

    private final List<Token> waitingPlayers;

    public GamesController(PublisherInterface publisher) {

        timer = new Timer();
        waitingPlayers = new ArrayList<>();

        timerTask = new TimerTask() {
            @Override
            public void run() {

                GameController newGame = new GameController(waitingPlayers,
                        publisherInterface);
                for (Token waitingPlayer : waitingPlayers) {
                    clients.put(waitingPlayer, newGame);
                }

                waitingPlayers.clear();

            }
        };

    }

    public void newPlayer(Token token) {

        waitingPlayers.add(token);

        if ((waitingPlayers.size() >= 2) && (waitingPlayers.size() <= 7)) {

            timerTask.cancel();
            timer.cancel();
            timer.schedule(timerTask, COUNTDOWN * 1000);

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

        if (!(request instanceof RequestMsg)) {
            return new InvalidRequestMsg("Invalid request (unrecognized).");
        }

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
