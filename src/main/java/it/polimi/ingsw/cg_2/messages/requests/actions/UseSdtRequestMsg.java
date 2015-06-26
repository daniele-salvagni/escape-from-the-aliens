package it.polimi.ingsw.cg_2.messages.requests.actions;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.controller.actions.ActionFactoryVisitor;
import it.polimi.ingsw.cg_2.messages.Token;

/**
 *
 */
public class UseSdtRequestMsg extends ActionRequestMsg {

    public UseSdtRequestMsg(Token token) {

        super(token);

    }

    @Override
    public Action createAction(ActionFactoryVisitor visitor) {

        return visitor.visit(this);

    }

}
