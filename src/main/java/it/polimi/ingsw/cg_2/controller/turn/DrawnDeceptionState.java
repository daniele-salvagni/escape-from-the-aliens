package it.polimi.ingsw.cg_2.controller.turn;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.controller.actions.NoiseAction;
import it.polimi.ingsw.cg_2.model.Game;

/**
 * This is a state of the state machine that manages a game. It represents
 * the state where a Player drawn a Deception card (so he must choose the
 * position where to make the noise).
 */
public class DrawnDeceptionState extends TurnState {

    private static final DrawnDeceptionState INSTANCE = new
            DrawnDeceptionState();

    /**
     * Get the instance of the DrawnDeceptionState singleton class.
     *
     * @return the instance of the singleton
     */
    public static DrawnDeceptionState getInstance() {

        return INSTANCE;

    }

    @Override
    public boolean isActionValid(Action action, Game game) {

        // Note: This is the correct usage of 'instanceof', using it to cast
        // a multitude of objects to their correct type it is considered a
        // bad practice, it is not for checking the equality to a certain type.

        // Check if the the action sequence is valid and then if the action
        // itself is valid.
        return (action instanceof NoiseAction) && action.isValid();

    }

}
