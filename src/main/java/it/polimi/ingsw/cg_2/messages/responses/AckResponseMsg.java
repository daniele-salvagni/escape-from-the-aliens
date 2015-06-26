package it.polimi.ingsw.cg_2.messages.responses;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

/**
 * An acknowledgment signal message.
 */
public class AckResponseMsg implements ResponseMsg {

    private final String message;

    /**
     * Create a new acknowledgment message without additional messages.
     */
    public AckResponseMsg() {

        this("");

    }

    /**
     * Create a new acknowledgment message with an additional message.
     *
     * @param message the additional message
     */
    public AckResponseMsg(String message) {

        this.message = message;

    }

    /**
     * Get the additional message of the acknowledgment message.
     *
     * @return the additional message
     */
    public String getMessage() {

        return message;

    }

    @Override
    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
