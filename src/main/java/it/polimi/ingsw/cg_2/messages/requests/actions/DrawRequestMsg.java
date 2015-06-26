package it.polimi.ingsw.cg_2.messages.requests.actions;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.controller.actions.ActionFactoryVisitor;
import it.polimi.ingsw.cg_2.messages.Token;

/**
 * A message to request to draw a sector card.
 */
public class DrawRequestMsg extends ActionRequestMsg {

    /**
     * Create a new DrawRequestMsg request message.
     *
     * @param token  the token that identifies the client
     */
    public DrawRequestMsg(Token token) {

        super(token);

    }

    @Override
    public Action createAction(ActionFactoryVisitor visitor) {

        return visitor.visit(this);

    }

}
