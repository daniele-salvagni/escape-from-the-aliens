package it.polimi.ingsw.cg_2.view.gamemanager;

import it.polimi.ingsw.cg_2.view.commons.BrokerInterface;

import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 * This SocketPubServer extends a {@link SocketServer} to manage connection requests for
 * the publisher-subscriber component. A {@link Broker} is used instead of a
 * RequestHandler and newHandler method creates a new SubscriberHandler instead of a
 * ClientHandler.
 */
public class SocketPubServer extends SocketServer {

    private final BrokerInterface broker;

    /**
     * Create a new Publisher socket server.
     *
     * @param port            the port where to listen for incoming connections
     * @param executorService the executor service to manage additional threads
     * @param broker          the broker interface of the pub-sub component
     */
    public SocketPubServer(int port, ExecutorService executorService, BrokerInterface
            broker) {

        super(port, executorService);
        this.broker = broker;

    }

    @Override
    protected SocketHandler newHandler(Socket socket) {

        return new SubscriberHandler(socket, broker);

    }

}
