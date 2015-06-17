package it.polimi.ingsw.cg_2.controller.turn;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.model.Game;

/**
 * This is a state of the state machine that manages a game. It represents
 * the state where a Player drawn a Deception card (so he must chose the
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
    public DrawnDeceptionState getInstance() {

        return INSTANCE;

    }

    @Override
    public boolean isActionValid(Action action, Game game) {

        return false;
    }

}
