package it.polimi.ingsw.cg_2.controller.turn;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.controller.actions.MoveAction;
import it.polimi.ingsw.cg_2.controller.actions.UseItemAction;
import it.polimi.ingsw.cg_2.model.Game;

/**
 * This is the initial state of the state machine that manages player turns.
 * Actions act as transitions, the execution of an action provides the
 * following state or null if it should not change (this allows to keep the
 * state machine as simple as possible and avoids the necessity to carry around
 * the payload of the current state).
 * <p/>
 * At this level we check if the <b>sequence<b/> of actions is valid, the
 * validity of the single atomic action is also checked by calling isValid on
 * the action itself.
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
     */

    @Override
    public boolean isActionValid(Action action, Game game) {

        // Note: This is the correct usage of 'instanceof', using it to cast
        // a multitude of objects to their correct type it is considered a
        // bad practice, it is not for checking the equality to a certain type.

        if ((action instanceof MoveAction) || (action instanceof
                UseItemAction)) {

            return action.isValid(game);

        } else {

            return false;

        }

    }

    /* From this state, the possible subsequent states (determined by the
     * execution of the transition action) could be:
     *
     * + MovedToSafeState
     * + MovedToDangerState
     */

    @Override
    public TurnState executeAction(Action action, Game game) {

        return (TurnState) action.execute();

    }
}
