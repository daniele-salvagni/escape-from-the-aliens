package it.polimi.ingsw.cg_2.controller.turn;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.model.Game;

/**
 * This interface must be implemented by the states of the TurnMachine FSM, it
 * represents a single state of the machine.
 */
public interface TurnState {

    /**
     * Check if the action is valid. At this level we check if the
     * <b>sequence<b/> of actions is valid, the validity of the single atomic
     * action should also be checked by calling isValid on the action itself.
     *
     * @param action the action (transition) to check
     * @param game   the game where to check the action
     * @return true, if the transition is valid
     */
    boolean isActionValid(Action action, Game game);

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
    TurnState executeAction(Action action, Game game);

}
