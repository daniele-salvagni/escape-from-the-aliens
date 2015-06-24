package it.polimi.ingsw.cg_2.controller.turn;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.model.Game;

/**
 * This is a state of the state machine that manages a game. It represents
 * the state where a game is finished, no further actions are allowed.
 */
public class FinishedState extends TurnState {

    private static final FinishedState INSTANCE = new FinishedState();

    /**
     * Get the instance of the FinishedState singleton class.
     *
     * @return the instance of the singleton
     */
    public static FinishedState getInstance() {

        return INSTANCE;

    }

    @Override
    public boolean isActionValid(Action action, Game game) {

        return false;

    }

}
