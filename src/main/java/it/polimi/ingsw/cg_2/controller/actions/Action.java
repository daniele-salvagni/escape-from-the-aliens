package it.polimi.ingsw.cg_2.controller.actions;

import it.polimi.ingsw.cg_2.messages.broadcast.BroadcastMsg;
import it.polimi.ingsw.cg_2.messages.responses.ResponseMsg;

/**
 * This class represents an atomic Action (like a command pattern) to be
 * executed on the server. It is possible to check if a generic Action is valid
 * and to execute it. The execution will return the following TurnState for the
 * turn control state machine (state pattern). After execution it is also able
 * to generate the private response for the client and the broadcast message to
 * inform all the players of the game.
 */
public interface Action {

    boolean isValid();

    // Implementations does return TurnState, but we need to break
    // dependencies between the client and the server somewhere. The Visitor
    // pattern works very well in this case, but it creates a lot of
    // dependencies. This can be done in different ways, but in every case it
    // does include the necessity to cast an object.
    Object execute();

    ResponseMsg getRespondeMesage();

    BroadcastMsg getBroadcastMessage();


}
