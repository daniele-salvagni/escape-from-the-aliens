package it.polimi.ingsw.cg_2.utils.exception;

/**
 * This exception is thrown when we detect problems while creating a
 * {@link Zone}.
 */
public class InvalidZoneException extends Exception {

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
