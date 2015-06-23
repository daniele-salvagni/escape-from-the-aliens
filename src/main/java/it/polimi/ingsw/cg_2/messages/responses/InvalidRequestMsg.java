package it.polimi.ingsw.cg_2.messages.responses;

/**
 * This response message is sent to the client if his request was not valid. It
 * could contain a reason (mainly for debugging purposes).
 */
public class InvalidRequestMsg implements ResponseMsg {

    private final String reason;

    /**
     * Creates a new InvalidRequestMsg without a reason.
     */
    public InvalidRequestMsg() {

        this("");

    }

    /**
     * Creates a new InvalidRequestMsg containing a reason.
     *
     * @param reason the reason why the request was invalid
     */
    public InvalidRequestMsg(String reason) {

        this.reason = reason;

    }

    /**
     * Get the reason why the message is invalid.
     *
     * @return the reason why the request was invalid
     */
    public String getReason() {

        return reason;
    }

}
