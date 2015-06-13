package it.polimi.ingsw.cg_2.utils.exception;

/**
 * This exception is thrown when an invalid {@link it.polimi.ingsw.cg_2
 * .messages.requests.RequestMsg} is detected.
 */
public class InvalidMsgException extends RuntimeException {

    private static final long serialVersionUID = -3549090288698293544L;

    /**
     * Constructs a new InvalidMsgException with no detail message.
     */
    public InvalidMsgException() {

        super();

    }

    /**
     * Constructs a new InvalidMsgException with the specified detail
     * message.
     *
     * @param s the detail message
     */
    public InvalidMsgException(String s) {

        super(s);

    }

}
