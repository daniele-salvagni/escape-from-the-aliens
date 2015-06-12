package it.polimi.ingsw.cg_2.controller.turn;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.model.Game;

/**
 *
 */
public class TurnMachine implements TurnState {

    TurnState currentState;

    Context context;

    @Override
    public boolean isActionValid(Action action, Game game, Context context) {

        return false;

    }

    @Override
    public TurnState executeAction(Action action, Game game, Context context) {

        return null;

    }
}
