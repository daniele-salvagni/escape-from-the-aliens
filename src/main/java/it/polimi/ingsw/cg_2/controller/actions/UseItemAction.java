package it.polimi.ingsw.cg_2.controller.actions;

import it.polimi.ingsw.cg_2.messages.ResultMsgPair;
import it.polimi.ingsw.cg_2.model.Game;

/**
 *
 */
public class UseItemAction implements Action {

    @Override
    public boolean isValid(Game game) {

        return false;
    }

    @Override
    public Object execute(Game game) {

        return null;
    }

    @Override
    public ResultMsgPair getResult() {

        return null;
    }

}
