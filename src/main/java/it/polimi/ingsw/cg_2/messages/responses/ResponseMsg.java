package it.polimi.ingsw.cg_2.messages.responses;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

import java.io.Serializable;

/**
 * This interface represents a Response message from the server to the client,
 * subsequent to a client request.
 */
public interface ResponseMsg extends Serializable {

    void display(MessageVisitor visitor);

}
