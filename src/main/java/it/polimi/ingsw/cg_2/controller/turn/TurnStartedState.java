package it.polimi.ingsw.cg_2.controller.turn;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.controller.actions.MoveAction;
import it.polimi.ingsw.cg_2.controller.actions.UseItemAction;
import it.polimi.ingsw.cg_2.model.Game;

/**
 * This is the initial state of the state machine that manages a Game. It
 * represents the beginning of the turn of a player.
 */
public enum TurnStartedState implements TurnState {

    INSTANCE;

    // Note: ENUM enforces the singleton property and it is better for
    // serialization (if needed) over the classic singletons.

    /* From this state, it is possible to go to perform the following
     * transition
     * actions:
     *
     * + MoveAction
     * + UseItemAction (Always valid, does not change the FSM state)
     */

    @Override
    public boolean isActionValid(Action action, Game game) {

        // Note: This is the correct usage of 'instanceof', using it to cast
        // a multitude of objects to their correct type it is considered a
        // bad practice, it is not for checking the equality to a certain type.

        // Check if the the action sequence is valid and then if the action
        // itself is valid.
        return ((action instanceof MoveAction) || (action instanceof
                UseItemAction)) && action.isValid(game);

    }

    /* From this state, the possible subsequent states (determined by the
     * execution of the transition action) could be:
     *
     * + MovedToSafeState
     * + MovedToDangerState
     * + FinishedState (Player escaped & Game finished)
     * + TurnStarted (Player escaped & Game not finished)
     */

    @Override
    public TurnState executeAction(Action action, Game game) {

        return (TurnState) action.execute(game);

    }

}
