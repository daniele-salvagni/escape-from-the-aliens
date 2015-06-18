package it.polimi.ingsw.cg_2.controller.turn;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.model.Game;

/**
 *
 */
public class DrawDeceptionAndItemState extends TurnState {

    @Override
    public boolean isActionValid(Action action, Game game) {

        return false;
    }

}
