package it.polimi.ingsw.cg_2.controller.turn;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.model.Game;

/**
 * This is a state of the state machine that manages a game. It represents
 * the state where a Player did try to escape while being inside an hatch sector
 * without success.
 */
public class TriedToEscapeState extends TurnState {

    public static final TriedToEscapeState INSTANCE = new TriedToEscapeState();

    /**
     * Get the instance of the TriedToEscapeState singleton class.
     *
     * @return the instance of the singleton
     */
    public TriedToEscapeState getInstance() {

        return INSTANCE;

    }

    @Override
    public boolean isActionValid(Action action, Game game) {

        return false;
    }

}
