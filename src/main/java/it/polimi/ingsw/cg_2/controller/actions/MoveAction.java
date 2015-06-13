package it.polimi.ingsw.cg_2.controller.actions;

import it.polimi.ingsw.cg_2.messages.broadcast.BroadcastMsg;
import it.polimi.ingsw.cg_2.messages.responses.ResponseMsg;

/**
 *
 */
public class MoveAction implements Action {

    @Override
    public boolean isValid() {

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
