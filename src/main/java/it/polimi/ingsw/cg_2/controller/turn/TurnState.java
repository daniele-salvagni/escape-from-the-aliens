package it.polimi.ingsw.cg_2.controller.turn;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.model.Game;

/**
 *
 */
public interface TurnState {

    boolean isActionValid(Action action, Game game, Context context);

    TurnState executeAction(Action action, Game game, Context context);

}
