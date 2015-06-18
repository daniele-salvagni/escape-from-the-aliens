package it.polimi.ingsw.cg_2.controller.turn;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.model.Game;

/**
 *
 */
public class DrawDeceptionAndItemState extends TurnState {

    private static final DrawDeceptionAndItemState INSTANCE = new
            DrawDeceptionAndItemState();

    /**
     * Get the instance of the DrawDeceptionAndItemState singleton class.
     *
     * @return the instance of the singleton
     */
    public static DrawDeceptionAndItemState getInstance() {

        return INSTANCE;

    }

    @Override
    public boolean isActionValid(Action action, Game game) {

        return false;
    }

}
