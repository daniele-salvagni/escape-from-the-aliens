package it.polimi.ingsw.cg_2.utils.exception;

/**
 * This exception is thrown when we detect problems with a {@link Zone}, we use
 * this to add a layer of abstraction over the lower level exceptions.
 */
public class InvalidZoneException extends RuntimeException {

    private static final long serialVersionUID = 3743959861630849966L;

    /**
     * Constructs a new InvalidZoneException with no detail message.
     */
    public InvalidZoneException() {

        super();

    }

    /**
     * Constructs a new InvalidZoneException with the specified detail message.
     *
     * @param s the detail message
     */
    public InvalidZoneException(String s) {

        super(s);

    }

}
