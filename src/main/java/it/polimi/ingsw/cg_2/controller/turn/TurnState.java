package it.polimi.ingsw.cg_2.controller.turn;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.model.Game;

/**
 * This abstact class must be extended by the states of the TurnMachine FSM, it
 * represents a single state of the machine.
 */
public abstract class TurnState {

    /**
     * Check if the action is valid. At this level we check if the
     * <b>sequence<b/> of actions is valid, the validity of the single atomic
     * action should also be checked by calling isValid on the action itself.
     *
     * @param action the action (transition) to check
     * @param game   the game where to check the action
     * @return true, if the transition is valid
     */
    public abstract boolean isActionValid(Action action, Game game);

    /**
     * Execute the action, the execution of an action provides the following
     * state or null if it should not change (this allows to keep the state
     * machine as simple as possible and avoids the necessity to carry around
     * the payload of the current state).
     *
     * @param action the action (transition) to execute
     * @param game   the game where to execute the action
     * @return the next state of the FSM, null if it should not change
     */
    public TurnState executeAction(Action action, Game game) {

        /* These situations could happen only by programming errors, we don't
         want to recover from that. */
        if (!isActionValid(action, game)) {

            throw new AssertionError("A transition should be applied only if " +
                    "valid");

        }

        return (TurnState) action.execute();

    }

}
