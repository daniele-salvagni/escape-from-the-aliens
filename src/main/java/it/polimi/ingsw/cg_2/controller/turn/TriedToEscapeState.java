package it.polimi.ingsw.cg_2.controller.turn;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.model.Game;

/**
 * This is a state of the state machine that manages a game. It represents
 * the state where a Player did try to escape while being inside an hatch sector
 * without success.
 */
public enum TriedToEscapeState implements TurnState {

    INSTANCE;

    @Override
    public boolean isActionValid(Action action, Game game) {

        return false;
    }

    @Override
    public TurnState executeAction(Action action, Game game) {

        return null;
    }

}
