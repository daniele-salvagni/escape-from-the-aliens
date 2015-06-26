package it.polimi.ingsw.cg_2.view.gameplayer;

import it.polimi.ingsw.cg_2.view.commons.BrokerInterface;
import it.polimi.ingsw.cg_2.view.commons.RequestHandler;

import java.io.IOException;

/**
 * The Socket implementation of the PlayerConnectionFactory, creates a new SocketClient
 * which implements both the RequestHandler and the BrokerInterface.
 */
public class SocketFactory extends PlayerConnectionFactory {

    private static final int SOCKET_SERVER_PORT = 1337;
    private static final int SOCKET_PUBLISHER_PORT = 1338;
    private final SocketClient socketClient;

    /**
     * Create a new SocketFactory.
     *
     * @param host        the hostname of the game manager
     * @param viewUpdater the view updater
     * @throws IOException if there was a problem setting up the connection
     */
    public SocketFactory(String host, ViewUpdater viewUpdater) throws IOException {

        super(viewUpdater);

        if (host == null) {
            throw new IllegalArgumentException("Host should not be null");
        }

        socketClient = new SocketClient(host, SOCKET_SERVER_PORT,
                SOCKET_PUBLISHER_PORT, viewUpdater, getSubscriber());

        socketClient.start();
        setupConnection();

    }

    @Override
    public RequestHandler getRequestHandler() {

        return socketClient;

    }

    @Override
    public BrokerInterface getBrokerInterface() {

        return socketClient;

    }
}
