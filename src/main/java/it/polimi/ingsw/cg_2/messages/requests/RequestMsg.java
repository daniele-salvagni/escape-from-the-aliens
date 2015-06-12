package it.polimi.ingsw.cg_2.messages.requests;

/**
 * This class represents a generic request message from the client to the
 * server, the client must identify himself by using an unique token obtained
 * from the server at the first connection. If it is the first connection the
 * token is allowed to be null.
 */
public abstract class RequestMsg {

    String token;

    /**
     * The constructor of this abstract class, it does set the unique token
     * used by the client to identify himself when sending a request.
     *
     * @param token the unique token to identify the client
     */
    public RequestMsg(String token) {

        this.token = token;

    }

    /**
     * Gets the unique token used used by the client to identify himself when
     * sending a request.
     *
     * @return the unique token that identifies the client
     */
    public String getToken() {

        return token;

    }

}
