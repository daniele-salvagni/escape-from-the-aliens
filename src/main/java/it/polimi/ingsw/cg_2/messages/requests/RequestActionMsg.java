package it.polimi.ingsw.cg_2.messages.requests;

import it.polimi.ingsw.cg_2.controller.actions.ActionCreator;

/**
 * This is the class for an action request message from the client to the
 * server, to perform an action the client must identify himself with an unique
 * token previously obtained from the server. Subclasses must implement the
 * {@link ActionCreator} class so the relative
 * {@link it.polimi.ingsw.cg_2.controller.actions.Action} can be created
 * polymorphically without knowing the type of request message thanks to the
 * visitor pattern.
 */
public abstract class RequestActionMsg implements ActionCreator {

    String token;

    /**
     * The constructor of this abstract class, it does set the unique token used
     * by the client to identify himself when sending a request.
     *
     * @param token the unique token to identify the client
     */
    public RequestActionMsg(String token) {

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
