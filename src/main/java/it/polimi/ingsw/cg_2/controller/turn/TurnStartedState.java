package it.polimi.ingsw.cg_2.controller.turn;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.controller.actions.MoveAction;
import it.polimi.ingsw.cg_2.controller.actions.UseItemAction;
import it.polimi.ingsw.cg_2.model.Game;

/**
 * This is the initial state of the state machine that manages a Game. It
 * represents the beginning of the turn of a player.
 */
public class TurnStartedState extends TurnState {

    private static final TurnStartedState INSTANCE = new TurnStartedState();

    /**
     * Get the instance of the TurnStartedState singleton class.
     *
     * @return the instance of the singleton
     */
    public TurnStartedState getInstance() {

        return INSTANCE;

    }

    /* From this state, it is possible to go to perform the following
     * transition
     * actions:
     *
     * + MoveAction
     * + UseItemAction (Always valid, does not change the FSM state)
     *
     * The possible subsequent states (determined by the execution of the
     * transition action) could be:
     *
     * + MovedToSafeState
     * + MovedToDangerState
     * + MovedToHatchState
     */

    @Override
    public boolean isActionValid(Action action, Game game) {

        // Note: This is the correct usage of 'instanceof', using it to cast
        // a multitude of objects to their correct type it is considered a
        // bad practice, it is not for checking the equality to a certain type.

        // Check if the the action sequence is valid and then if the action
        // itself is valid.
        return ((action instanceof MoveAction) || (action instanceof
                UseItemAction)) && action.isValid();

    }

}
