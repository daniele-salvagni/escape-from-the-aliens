package it.polimi.ingsw.cg_2.controller.turn;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.model.Game;

/**
 * This is a state of the state machine that manages a game. It represents
 * the state where a Player has moved to a Safe Sector.
 */
public class MovedToSafeState extends TurnState {

    private static final MovedToSafeState INSTANCE = new MovedToSafeState();

    /**
     * Get the instance of the MovedToSafeState singleton class.
     *
     * @return the instance of the singleton
     */
    public MovedToSafeState getInstance() {

        return INSTANCE;

    }

    @Override
    public boolean isActionValid(Action action, Game game) {

        return false;
    }

}
