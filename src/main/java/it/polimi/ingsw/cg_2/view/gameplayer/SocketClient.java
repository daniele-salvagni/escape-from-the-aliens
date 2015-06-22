package it.polimi.ingsw.cg_2.view.gameplayer;

import it.polimi.ingsw.cg_2.messages.Token;
import it.polimi.ingsw.cg_2.messages.broadcast.BroadcastMsg;
import it.polimi.ingsw.cg_2.messages.requests.ConnectionRequest;
import it.polimi.ingsw.cg_2.messages.requests.RequestMsg;
import it.polimi.ingsw.cg_2.messages.responses.ConnectionResponseMsg;
import it.polimi.ingsw.cg_2.messages.responses.ResponseMsg;
import it.polimi.ingsw.cg_2.view.commons.BrokerInterface;
import it.polimi.ingsw.cg_2.view.commons.RequestHandler;
import it.polimi.ingsw.cg_2.view.commons.SubscriberInterface;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles the client (request-response) and the subscriber for the game player.
 * Implements BrokerInterface and RequestHandler, the interfaces that are also remotely
 * used by the RMI implementation.
 * <p/>
 * After subscription messages are dispatched to the SubscriberInterface (the same
 * remotely used by the RMI implementation).
 * <p/>
 * It is possible to ask for a new connection (a Token will be received to authenticate
 * the client), send requests to the server (and subsequently receive a response),
 * subscribe or unsubscribe from a specific topic (in this case to a game) and
 * asynchronously receive messages from the subscribed topics.
 */
public class SocketClient extends Thread implements BrokerInterface,
        RequestHandler {

    private static final Logger LOG = Logger.getLogger(SocketClient.class.getName());

    // Maximum timeout to receive a response trough a socket
    private static final int TIMEOUT = 3000;

    // The class that updates the view with the received messages
    private final ViewUpdater viewUpdater;
    // The SubscriberInterface where to dispatch pub-sub messages
    private final SubscriberInterface subscriberInterface;

    // Socket configurations
    private final String host;
    private final int serverPort;
    private final int pubPort;

    private Socket pubSocket;
    private ObjectInputStream pubInputStream;

    // True after a successful subscription
    private AtomicBoolean subscribed;

    /**
     * Creates a new SocketClient for managing the client and the subscriber for the game
     * player.
     *
     * @param host                the hostname of the game manager
     * @param serverPort          the port of the game manager server
     * @param pubPort             the port of the game manager publisher
     * @param viewUpdater         the view updater to display server responses
     * @param subscriberInterface the subscriber where to dispatch
     * @throws IOException              if there is a problem connecting to the publisher
     * @throws IllegalArgumentException if an argument is invalid (null)
     */
    public SocketClient(String host, int serverPort, int pubPort, ViewUpdater
            viewUpdater, SubscriberInterface subscriberInterface) throws IOException,
            IllegalArgumentException {

        if (host == null) {
            throw new IllegalArgumentException("Host cannot be null.");
        } else if (viewUpdater == null) {
            throw new IllegalArgumentException("ViewUpdater cannot be null.");
        } else if (subscriberInterface == null) {
            throw new IllegalArgumentException("SubscriberInterface cannot be null.");
        }

        this.host = host;
        this.serverPort = serverPort;
        this.pubPort = pubPort;

        this.viewUpdater = viewUpdater;
        this.subscriberInterface = subscriberInterface;

        // Setup the connection to the publisher
        pubSocket = new Socket(host, pubPort);
        subscribed = new AtomicBoolean(false);

    }

    /**
     * Create a new connection to the server, send a request, wait for a response (until
     * TIMEOUT occurs) and close the connection. In this method is handled the lower
     * level
     * part of the [Request-Response] connection.
     *
     * @param request the request message to send to the server
     * @return the response from the server
     *
     * @throws IOException            if there was a problem somewhere during the
     *                                connection or if it times out
     * @throws ClassNotFoundException if there was a problem reading the response object
     */
    private Object sendServerRequest(RequestMsg request) throws IOException,
            ClassNotFoundException {

        // Create a connection to the server
        Socket server = new Socket(host, serverPort);
        // Set a maximum timeout to receive a response. If the timeout expires, a java
        // .net.SocketTimeoutException is raised, though the Socket is still valid.
        server.setSoTimeout(TIMEOUT);

        ObjectOutputStream serverOutputStream = new ObjectOutputStream(server
                .getOutputStream());

        serverOutputStream.writeObject(request);
        // Flushes the stream. This will write any buffered output bytes and flush
        // through to the underlying stream.
        serverOutputStream.flush();

        ObjectInputStream serverInputStream = new ObjectInputStream(server
                .getInputStream());

        // Exceptions are thrown for problems with the InputStream and for classes that
        // should not be deserialized.
        Object response = serverInputStream.readObject();

        // Closes this socket. Any thread currently blocked in an I/O operation upon
        // this socket will throw a SocketException. (Request-Response "pattern", after
        // a response we always close the connection)
        server.close();

        return response;

    }

    /**
     * Send a request to be processed on the server and wait for a response.
     *
     * @param request the request to be processed by the server
     * @return the response obtained from the server
     *
     * @throws RemoteException if a problem occurs during the connection or if it times
     *                         out
     */
    public ResponseMsg processRequest(RequestMsg request) throws RemoteException {

        try {

            Object response = sendServerRequest(request);

            if (response instanceof ResponseMsg) {

                return (ResponseMsg) response;

            } // Else a RemoteException is thrown

        } catch (IOException e) {
            LOG.log(Level.SEVERE, "There was a problem with the server request.", e);
        } catch (ClassNotFoundException e) {
            LOG.log(Level.SEVERE, "An invalid message has been returned from the " +
                    "server", e);
        }

        throw new RemoteException("An error occurred while processing a request.");

    }

    @Override
    public Token connect() throws RemoteException {

        // Send a connection request with a null Token (ask for a new one, it is a
        // new connection)
        ResponseMsg response = processRequest(new ConnectionRequest(null));

        if (response instanceof ConnectionResponseMsg) {
            // Cast to ConnectionResponseMsg and get the received Token
            return ((ConnectionResponseMsg) response).getToken();
        } // Else a RemoteException is thrown

        LOG.log(Level.SEVERE, "There was a problem while establishing a new connection" +
                " with the server.");
        throw new RemoteException("Error while establishing a new connection to the " +
                "server.");

    }

    @Override
    public void subscribe(SubscriberInterface subscriber, Token token) throws
            RemoteException {

        Object response = null;

        try {

            try {

                synchronized (pubSocket) {
                    ObjectOutputStream publisherOutputStream = new ObjectOutputStream
                            (pubSocket.getOutputStream());

                    publisherOutputStream.writeObject(new SubscribeRequestMsg(token));
                    publisherOutputStream.flush();
                }

            } catch (IOException e) {
                throw new RemoteException("Error while writing to the socket.", e);
            }

            try {

                synchronized (pubSocket) {
                    pubSocket.setSoTimeout(TIMEOUT);
                    pubInputStream = new ObjectInputStream(pubSocket.getInputStream());
                    response = pubInputStream.readObject();
                    // Put the timeout to unlimited (after the subscription we don't
                    // need a timeout since we need to wait "forever")
                    pubSocket.setSoTimeout(0);
                }

            } catch (IOException e) {
                throw new RemoteException("Error while reading from the socket.", e);
            } catch (ClassNotFoundException e) {
                throw new RemoteException("An invalid message has been returned from " +
                        "the " +

                        "server.", e);
            }

            try {
                pubSocket.shutdownOutput();
            } catch (IOException e) {
                throw new RemoteException("Unable to shutdown output.", e);
            }

        } catch (RemoteException remoteException) {

            try {
                synchronized (pubSocket) {
                    pubSocket.close();
                }
            } catch (IOException e) {
                LOG.log(Level.SEVERE, "An error occurred while closing the socket.", e);
            }

            throw remoteException;

        }

        if (!(response instanceof SuccessResponseMsg)) {
            throw new IllegalArgumentException("Bad subscribe arguments.");
        }

        setSubscribed(true);

    }

    @Override
    public void unsubscribe(SubscriberInterface subscriber) throws RemoteException,
            IllegalArgumentException {

        try {

            synchronized (pubSocket) {
                // Close the connection to unsubscribe
                pubSocket.close();
            }

        } catch (IOException e) {
            throw new RemoteException("An error occurred while closing the connection" +
                    ".", e);
        }

        setSubscribed(false);

    }

    /**
     * Set the subscription status.
     *
     * @param subscribed the subscription
     */
    private void setSubscribed(boolean subscribed) {

        synchronized (this.subscribed) {
            this.subscribed.set(subscribed);
            this.subscribed.notifyAll();
        }

    }

    /**
     * Wait for a successful subscription.
     */
    private void waitForSubscription() {

        synchronized (subscribed) {
            while (!subscribed.get()) {

                try {
                    subscribed.wait();
                } catch (InterruptedException e) {
                    LOG.log(Level.SEVERE, "InterruptedException occurred.", e);
                }

            }

        }

    }

    @Override
    public void run() {

        try {
            while (true) {

                waitForSubscription();

                Object received = null;

                synchronized (pubSocket) {

                    // Set the timeout to unlimited and wait for a message to be received
                    pubSocket.setSoTimeout(0);
                    received = pubInputStream.readObject();

                }

                if (received instanceof BroadcastMsg) {
                    BroadcastMsg responseMessage = (BroadcastMsg) received;
                    subscriberInterface.dispatchMessage(responseMessage);
                }

            }
        } catch (ClassNotFoundException e) {
            LOG.log(Level.SEVERE, "Error while receiving a subscribe message.", e);
        } catch (RemoteException e) {
            LOG.log(Level.SEVERE, "Error in the subscribe connection.", e);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Error occurred while reading from socket.", e);
        }

    }


}
