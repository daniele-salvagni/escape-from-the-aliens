package it.polimi.ingsw.cg_2.messages.requests.actions;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.controller.actions.ActionFactoryVisitor;
import it.polimi.ingsw.cg_2.messages.Token;

/**
 *
 */
public class AttackRequestMsg extends ActionRequestMsg {

    public AttackRequestMsg(Token token) {

        super(token);

    }

    @Override
    public Action createAction(ActionFactoryVisitor visitor) {

        return visitor.visit(this);

    }

}
