package it.polimi.ingsw.cg_2.view.commons;

import it.polimi.ingsw.cg_2.messages.Token;
import it.polimi.ingsw.cg_2.messages.requests.RequestMsg;
import it.polimi.ingsw.cg_2.messages.responses.ResponseMsg;

import java.rmi.RemoteException;

/**
 * Handles request messages from a client and return an appropriate response message.
 */
public interface RequestHandler {

    /**
     * Ask the server for a new connection by sending a ConnectRequestMsg with a null
     * Token (otherwise it would be a reconnection). A new Token will be returned, the
     * client must use it to identify himself.
     *
     * @return a new Token to identify the client
     *
     * @throws RemoteException if a problem occurs during the connection or if it times
     *                         out
     */
    Token connect() throws RemoteException;

    /**
     * Send a request to be processed on the server and wait for a response.
     *
     * @param request the request to be processed by the server
     * @return the response obtained from the server
     *
     * @throws RemoteException if a problem occurs during the connection or if it times
     *                         out
     */
    ResponseMsg processRequest(RequestMsg request) throws RemoteException;

}
