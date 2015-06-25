package it.polimi.ingsw.cg_2.view.gameplayer;

import it.polimi.ingsw.cg_2.view.commons.BrokerInterface;
import it.polimi.ingsw.cg_2.view.commons.RequestHandler;

import java.io.IOException;

/**
 *
 */
public class SocketFactory extends PlayerConnectionFactory {

    private static final int SOCKET_SERVER_PORT = 1337;
    private static final int SOCKET_PUBLISHER_PORT = 1338;
    private final SocketClient socketClient;

    public SocketFactory(String host, ViewUpdater viewUpdater) throws IOException,
            IllegalArgumentException {

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
