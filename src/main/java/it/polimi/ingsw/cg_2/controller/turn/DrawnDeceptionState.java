package it.polimi.ingsw.cg_2.controller.turn;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.model.Game;

/**
 * This is a state of the state machine that manages a game. It represents
 * the state where a Player drawn a Deception card (so he must chose the
 * position where to make the noise).
 */
public enum DrawnDeceptionState implements TurnState {

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
