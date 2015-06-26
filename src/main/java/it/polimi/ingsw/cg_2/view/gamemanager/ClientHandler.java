package it.polimi.ingsw.cg_2.view.gamemanager;

import it.polimi.ingsw.cg_2.messages.requests.RequestMsg;
import it.polimi.ingsw.cg_2.messages.responses.InvalidRequestMsg;
import it.polimi.ingsw.cg_2.messages.responses.ResponseMsg;
import it.polimi.ingsw.cg_2.view.commons.RequestHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles a single request-response connection from a client. After receiving a request
 * for a new socket we send an appropriate response and we immediately close the
 * connection.
 */
public class ClientHandler extends SocketHandler {

    private static final Logger LOG = Logger.getLogger(ClientHandler.class.getName());

    private static final int TIMEOUT = 3000;
    private final RequestHandler requestHandler;

    /**
     * Creates a new client handler.
     *
     * @param client the socket used by the client
     * @param requestHandler the interface to handle client requests
     */
    public ClientHandler(Socket client, RequestHandler requestHandler) {

        super(client);
        this.requestHandler = requestHandler;

    }

    /**
     * Receive a request message object from a client.
     *
     * @return the object received from the client
     *
     * @throws ClassNotFoundException problems while reading from the input stream
     * @throws IOException            in case of error while receiving message from
     *                                client
     */
    protected Object receiveObject() throws ClassNotFoundException, IOException {

        Object receivedObject;

        try {

            getSocket().setSoTimeout(TIMEOUT);

            ObjectInputStream inputStream = new ObjectInputStream(getSocket()
                    .getInputStream());

            receivedObject = inputStream.readObject();

        } catch (SocketException e) {
            LOG.log(Level.WARNING, "Error while setting socket timeout.", e);
            throw e;
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Error while receiving message from client.", e);
            throw e;
        } catch (ClassNotFoundException e) {
            LOG.log(Level.WARNING, "Can't read from input stream.", e);
            throw e;
        }

        return receivedObject;

    }

    /**
     * Send a response to the client after receiving a request, this also closes the
     * connection.
     *
     * @param response the response to send to the client
     */
    protected void sendResponse(Object response) {

        try {

            ObjectOutputStream outputStream = new ObjectOutputStream(getSocket()
                    .getOutputStream());
            if (response != null) {
                outputStream.writeObject(response);
            } else {
                outputStream.writeObject(new InvalidRequestMsg("There was a problem " +
                        "handling the request."));
            }
            outputStream.flush();

        } catch (IOException e) {
            LOG.log(Level.WARNING, "Can't reply to the client.", e);
        } finally {
            try {
                getSocket().close();
                // Response sent (or failed, hopefully not), try to close the connection
            } catch (IOException e) {
                LOG.log(Level.WARNING, "Exception while trying to close the socket.", e);
            }
        }

    }

    @Override
    public void run() {

        ResponseMsg response;

        try {

            Object request = receiveObject();

            if (request instanceof RequestMsg) {
                response = requestHandler.processRequest((RequestMsg) request);
            } else {
                response = new InvalidRequestMsg("Unknown request.");
            }

            sendResponse(response);


        } catch (IOException | ClassNotFoundException e) {
            LOG.log(Level.WARNING, "Unhandled errors during client-server communication" +
                    ".", e);
        }

    }

}
