package it.polimi.ingsw.cg_2.view.gameplayer;

import it.polimi.ingsw.cg_2.messages.Token;
import it.polimi.ingsw.cg_2.view.commons.BrokerInterface;
import it.polimi.ingsw.cg_2.view.commons.RequestHandler;
import it.polimi.ingsw.cg_2.view.commons.SubscriberInterface;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 *
 */
public class SocketClient extends Thread implements BrokerInterface,
        RequestHandler {

    private ViewUpdater viewUpdater;
    private SubscriberInterface subscriberInterface;

    private final String host;
    private final int serverPort;
    private final int publisherPort;

    private Socket publisherSocket;
    private ObjectInputStream publisherInputStream;

    protected AtomicBoolean subscribed;


    public SocketClient



    @Override
    public void subscribe(SubscriberInterface subscriber, Token token) {





    }

    @Override
    public Token connect() {

        return null;

    }

}
