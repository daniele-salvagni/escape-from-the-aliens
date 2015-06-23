package it.polimi.ingsw.cg_2.view.gamemanager;

import it.polimi.ingsw.cg_2.messages.requests.RequestMsg;
import it.polimi.ingsw.cg_2.messages.responses.InvalidRequestMsg;
import it.polimi.ingsw.cg_2.view.commons.RequestHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles the connection with the client for request-response messages communication.
 */
public class ClientHandler extends SocketHandler {

    private static final Logger LOG = Logger.getLogger(ClientHandler.class.getName());

    private static final int TIMEOUT = 3000;
    private final RequestHandler requestHandler;

    public ClientHandler(Socket client, RequestHandler requestHandler) {

        super(client);
        this.requestHandler = requestHandler;

    }

    protected Object receiveObject() throws ClassNotFoundException, IOException {

        Object receivedObject = null;
        ObjectInputStream ois = null;

        try {

            getSocket().setSoTimeout(TIMEOUT);

            ObjectInputStream inputStream = new ObjectInputStream(getSocket()
                    .getInputStream());

            Object object = inputStream.readObject();

            if(!(object instanceof RequestMsg)) {
                throw new ClassNotFoundException("Received an invalid object.");
            }


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

    protected void sendResponse(Object response) throws IOException {

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
            } catch (IOException e) {
                LOG.log(Level.WARNING, "Exception while closing the socket.", e);
            }
        }

    }

    @Override
    public void run() {

        Object returnedObject;

        try {
            returnedObject = receiveObject();
            sendResponse(returnedObject);
        } catch (IOException | ClassNotFoundException e) {
            LOG.log(Level.WARNING, "Unhandled errors during client-server communication" +
                    ".", e);
        }

    }

}
