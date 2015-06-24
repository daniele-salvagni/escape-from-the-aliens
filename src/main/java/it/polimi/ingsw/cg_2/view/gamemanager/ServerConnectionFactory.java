package it.polimi.ingsw.cg_2.view.gamemanager;

import it.polimi.ingsw.cg_2.view.commons.BrokerInterface;
import it.polimi.ingsw.cg_2.view.commons.RequestHandler;

import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Creates both RMI and Socket connections for request-response Server and
 * publisher-subscriber Broker.
 */
public class ServerConnectionFactory {

    private static final Logger LOG = Logger.getLogger(ServerConnectionFactory.class
            .getName());

    private final static int RMI_PORT = 7777;
    private final static int SOCKET_SERVER_PORT = 1337;
    private final static int SOCKET_PUBLISHER_PORT = 1338;
    private final static int MAX_SUB_THREADS = 24;

    private RequestHandler requestHandler;
    private final Broker broker;
    private Registry registry;
    private SocketServer server, publisher;

    /**
     * Create a new ServerConnectionFactory, a new Broker is automatically initialized.
     */
    public ServerConnectionFactory() {

        broker = new Broker();

    }

    /**
     * Set the request handler for the server to process requests.
     *
     * @param requestHandler the request handler for the server
     */
    public void setRequestHandler(RequestHandler requestHandler) {

        this.requestHandler = requestHandler;

    }

    /**
     * Get the Publisher Interface created at instantiation (the Broker).
     *
     * @return get the publisher interface
     */
    public PublisherInterface getPublisherInterface() {

        return broker;

    }

    /**
     * Start both RMI and SOCKET connections.
     */
    public void startServers() {

        if (requestHandler == null) {
            throw new IllegalStateException("RequestHandler is not set.");
        }

        initRMI();
        initSocket();

    }

    /**
     * Stop both RMI and SOCKET connections.
     */
    public void stopServers() {

        releaseRMI();
        releaseSocket();

    }

    /**
     * Initialize RMI communication.
     */
    private void initRMI() {

        try {

            // Creates and exports a Registry instance on the local host that accepts
            // requests on the specified port.
            registry = LocateRegistry.createRegistry(RMI_PORT);

            // Exports the remote object to make it available to receive incoming
            // calls, using the particular supplied port. (0 means it is automatically
            // chosen)
            BrokerInterface brokerStub = (BrokerInterface) UnicastRemoteObject
                    .exportObject(broker, 0);

            // Replaces the binding for the specified name in this registry with the
            // supplied remote reference. Exported Broker will be used by clients to
            // Subscribe / Unsubscribe
            registry.rebind("Broker", brokerStub);

            RequestHandler reqHandlerStub = (RequestHandler) UnicastRemoteObject
                    .exportObject(requestHandler, 0);

            // Exported RequestHandler will be used by clients to send requests and
            // receive responses.
            registry.rebind("RequestHandler", reqHandlerStub);


        } catch (RemoteException e) {
            LOG.log(Level.SEVERE, "Can't initialize RMI Registry.", e);
        }

    }

    /**
     * Release RMI resources.
     */
    private void releaseRMI() {

        try {

            // Removes the binding for the specified name in this registry
            registry.unbind("RequestHandler");
            // Removes the remote object from the RMI runtime
            UnicastRemoteObject.unexportObject(requestHandler, true);
            registry.unbind("Broker");
            UnicastRemoteObject.unexportObject(broker, true);

        } catch (RemoteException | NotBoundException e) {
            LOG.log(Level.WARNING, "Exception thrown while releasing RMI.", e);
        }

    }

    /**
     * Initialize Socket communication.
     */
    private void initSocket() {

        try {

            server = new SocketServer(SOCKET_SERVER_PORT, Executors.newCachedThreadPool
                    (), requestHandler);
            publisher = new SocketPubServer(SOCKET_PUBLISHER_PORT, Executors
                    .newFixedThreadPool(MAX_SUB_THREADS), broker);

            server.start();
            publisher.start();

        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Can't initialize Socket server.", e);
        }

    }

    /**
     * Release Socket resources.
     */
    private void releaseSocket() {

        publisher.stopServer();
        server.stopServer();

    }

}
