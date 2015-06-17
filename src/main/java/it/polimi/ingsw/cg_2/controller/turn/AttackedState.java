package it.polimi.ingsw.cg_2.controller.turn;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.model.Game;

/**
 * This is a state of the state machine that manages a game. It represents
 * the state where a Player performed an attack (successful or not).
 */
public class AttackedState extends TurnState {

    public static final AttackedState INSTANCE = new AttackedState();

    /**
     * Get the instance of the AttackedState singleton class.
     *
     * @return the instance of the singleton
     */
    public AttackedState getInstance() {

        return INSTANCE;

    }

    @Override
    public boolean isActionValid(Action action, Game game) {

        return false;
    }

}
