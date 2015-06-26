package it.polimi.ingsw.cg_2.messages.requests;

import it.polimi.ingsw.cg_2.messages.Token;

/**
 * A message to request a connection to the server.
 */
public class ConnectionRequest extends RequestMsg {

    /**
     * Create a new ConnectionRequest request message.
     *
     * @param token the token that identifies the client
     */
    public ConnectionRequest(Token token) {

        super(token);

    }

}
