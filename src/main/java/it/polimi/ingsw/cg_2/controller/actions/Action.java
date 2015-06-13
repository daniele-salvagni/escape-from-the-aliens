package it.polimi.ingsw.cg_2.controller.actions;

import it.polimi.ingsw.cg_2.messages.ResultMsgPair;
import it.polimi.ingsw.cg_2.model.Game;

/**
 * This class represents an atomic Action (like a command pattern) to be
 * executed on the server. It is possible to check if a generic Action is valid
 * and to execute it. The execution will return the following TurnState for the
 * turn control state machine (state pattern). After execution it is also able
 * to generate the private response for the client and the broadcast message to
 * inform all the players of the game.
 */
public interface Action {

    /**
     * Check if the atomic action is valid according to the rules of the game.
     * This checks only the single action, and not if the sequence is valid
     * inside a turn (this is checked before following a "chain of
     * responsibility" principle).
     *
     * @return true, if the atomic action is valid
     */
    boolean isValid(Game game);

    /**
     * Executes the action. After the execution it does return the following
     * {@link it.polimi.ingsw.cg_2.controller.turn.TurnState} of the state
     * machine that controls the sequence of actions. Null is returned if the
     * state should not change (this keeps the state machine as simple as
     * possible, and it removes the payload of carrying around the information
     * about the current status).
     * <p/>
     * Implementations should always return a TurnStatus object, but we need to
     * break the dependencies between the client and server somewhere. The
     * visitor pattern works very well in this case, but it does introduce a
     * lot
     * of dependencies. There are other ways to break them but in every case it
     * would involve the casting of an object and this is the place where it
     * makes more sense.
     *
     * @return the next TurnState of the state machine controlling the sequence
     * of actions
     */
    Object execute(Game game);

    /**
     * Returns both the private (response) and public (broadcast) result
     * message pair after the execution of the action.
     *
     * @return the private and public result message pair
     */
    ResultMsgPair getResult();

}
