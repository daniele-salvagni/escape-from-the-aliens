package it.polimi.ingsw.cg_2.view.gamemanager;

import it.polimi.ingsw.cg_2.messages.broadcast.BroadcastMsg;
import it.polimi.ingsw.cg_2.messages.requests.SubscribeRequestMsg;
import it.polimi.ingsw.cg_2.messages.responses.ResponseMsg;
import it.polimi.ingsw.cg_2.messages.responses.SubscribeResponseMsg;
import it.polimi.ingsw.cg_2.view.commons.BrokerInterface;
import it.polimi.ingsw.cg_2.view.commons.SubscriberInterface;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handle the subscriber in the publisher-subscriber component. It receives subscription
 * requests and after a successful subscription all the messages received from the
 * dispatchMessage method are processed from the queue and sent to the subscriber.
 */
public class SubscriberHandler extends SocketHandler implements SubscriberInterface {

    private static final Logger LOG = Logger.getLogger(SubscriberHandler.class.getName());
    private static final int TIMEOUT = 3000;

    private final BrokerInterface brokerInterface;

    private final Queue<BroadcastMsg> broadcastBuffer;
    private ObjectOutputStream outputStream;

    boolean isSubscribed;

    /**
     * Create a new subscriberhandler.
     *
     * @param socket the socket connection
     * @param brokerInterface the broker interface used to setup new subscriptions
     */
    public SubscriberHandler(Socket socket, BrokerInterface brokerInterface) {

        super(socket);
        this.brokerInterface = brokerInterface;

        broadcastBuffer = new ConcurrentLinkedQueue<>();

        isSubscribed = false;

    }

    /**
     * Manages a subscription request by dispatching it to the broker interface.
     *
     * @param subscribeRequest the subscription request
     * @throws IOException if a problem occurred during subscription
     */
    private void manageSubscription(SubscribeRequestMsg subscribeRequest) throws
            IOException {

        try {

            brokerInterface.subscribe(this, subscribeRequest.getToken());
            isSubscribed = true;

        } catch (RemoteException e) {
            LOG.log(Level.WARNING, "Remote exception during subscription.", e);
        }

        ResponseMsg responseSub = new SubscribeResponseMsg(isSubscribed);
        sendMsg(responseSub);

    }

    /**
     * Listens for a subscription request.
     *
     * @return the subscription request message
     */
    private SubscribeRequestMsg receiveSubscriptionRequest() {

        // Return null if there is a problem
        SubscribeRequestMsg subscribeMessage = null;

        try {

            getSocket().setSoTimeout(TIMEOUT);
            ObjectInputStream inputStream = new ObjectInputStream(getSocket()
                    .getInputStream());
            Object object = inputStream.readObject();
            if (!(object instanceof SubscribeRequestMsg))
                throw new ClassCastException();
            subscribeMessage = (SubscribeRequestMsg) object;

            // Close the input half od the socket
            try {
                getSocket().shutdownInput();
            } catch (IOException e) {
                LOG.log(Level.WARNING, "Error while shutting down input.", e);
            }

        } catch (SocketException e) {
            LOG.log(Level.WARNING, "Unhandled socket exception.", e);
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Error while reading from input.", e);
        } catch (ClassNotFoundException e) {
            LOG.log(Level.WARNING, "Received invalid message from client.", e);
        }

        return subscribeMessage;

    }

    /**
     * Manages the unsubscription of this client from the broker.
     */
    private void unsubscribe() {

        if (isSubscribed) {
            try {
                brokerInterface.unsubscribe(this);
                isSubscribed = false;
            } catch (RemoteException e) {
                LOG.log(Level.WARNING, "Unhandled remote exception.", e);
            } catch (IllegalArgumentException e) {
                LOG.log(Level.WARNING, "Error while unsubscribing.", e);
            }
        }

    }

    /**
     * Sends a message to the subscriber.
     *
     * @param message the message to send
     * @throws IOException if there was a problem during the transfer of the message
     */
    private void sendMsg(Object message) throws IOException {

        try {
            if (outputStream == null) {
                outputStream = new ObjectOutputStream(getSocket().getOutputStream());
            }

            outputStream.writeObject(message);
            outputStream.flush();
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Error while writing to the output stream.", e);
        }

    }

    /**
     * Send to the subscriber the content of the broadcastBuffer.
     *
     * @throws IOException if there was a problem during the transfer of the message
     * @throws InterruptedException if there was an interrupted exception
     */
    private void broadcastBuffer() throws IOException, InterruptedException {

        synchronized (broadcastBuffer) {

            while (broadcastBuffer.isEmpty()) {

                try {
                    broadcastBuffer.wait();
                } catch (InterruptedException e) {
                    throw e;
                }

            }

            BroadcastMsg broadcastMessage;
            do {
                broadcastMessage = broadcastBuffer.poll();
                if (broadcastMessage != null)
                    sendMsg(broadcastMessage);
            } while (broadcastMessage != null);

        }

    }

    @Override
    public void run() {

        try {

            SubscribeRequestMsg subscribeRequest;
            subscribeRequest = receiveSubscriptionRequest();
            manageSubscription(subscribeRequest);

            while (true) {
                broadcastBuffer();
            }

        } catch (IOException e) {

            unsubscribe();

        } catch (InterruptedException e) {
            LOG.log(Level.WARNING, "InterruptedException occurred.");
        } finally {
            try {
                getSocket().close();
            } catch (IOException e) {
                LOG.log(Level.SEVERE, "Can't close socket.", e);
            }
        }

    }

    @Override
    public void dispatchMessage(BroadcastMsg message) throws RemoteException {

        broadcastBuffer.add(message);
        synchronized (broadcastBuffer) {
            broadcastBuffer.notify();
        }

    }

}
