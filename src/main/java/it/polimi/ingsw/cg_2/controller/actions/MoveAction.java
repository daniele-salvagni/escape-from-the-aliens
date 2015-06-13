package it.polimi.ingsw.cg_2.controller.actions;

import it.polimi.ingsw.cg_2.messages.broadcast.BroadcastMsg;
import it.polimi.ingsw.cg_2.messages.responses.ResponseMsg;
import it.polimi.ingsw.cg_2.model.Game;

/**
 *
 */
public class MoveAction implements Action {

    @Override
    public boolean isValid(Game game) {

        return false;
    }

    @Override
    public Object execute() {

        return null;
    }

    @Override
    public ResponseMsg getResponseMessage() {

        return null;
    }

    @Override
    public BroadcastMsg getBroadcastMessage() {

        return null;
    }
}
