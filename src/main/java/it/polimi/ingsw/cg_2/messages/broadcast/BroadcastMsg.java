package it.polimi.ingsw.cg_2.messages.broadcast;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

import java.io.Serializable;

/**
 * This interface represents a generic broadcast message, every message must implement the
 * Visitor pattern to be displayed properly by the client.
 */
public interface BroadcastMsg extends Serializable {

    /**
     * Visit this message to display the result.
     *
     * @param visitor the visitor
     */
    void display(MessageVisitor visitor);

}
