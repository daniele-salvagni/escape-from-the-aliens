package it.polimi.ingsw.cg_2.messages.responses.actions;

import it.polimi.ingsw.cg_2.messages.responses.ResponseMsg;

/**
 * This abstract class represents a Response message from the server to the
 * client, subsequent to a client request and the execution of the related
 * action.
 */
public abstract class ActionResponseMsg implements ResponseMsg {

    private final boolean success;

    /**
     * Create a new ResponseMsg subsequent to a client request and the
     * execution
     * of the related action.
     *
     * @param success the success status of the action
     */
    public ActionResponseMsg(boolean success) {

        this.success = success;

    }

    /**
     * Check the success status of the action.
     *
     * @return the success status of the action
     */
    public boolean success() {

        return success;

    }

}
