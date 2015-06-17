package it.polimi.ingsw.cg_2.messages.responses;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.controller.turn.TurnState;
import it.polimi.ingsw.cg_2.model.Game;

public enum PassResponseMsg implements TurnState {

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
