package it.polimi.ingsw.cg_2.messages.requests.actions;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.controller.actions.ActionFactoryVisitor;
import it.polimi.ingsw.cg_2.messages.Token;

/**
 * A message to request to escape fro a hatch sector.
 */
public class EscapeRequestMsg extends ActionRequestMsg {

    /**
     * Create a new EscapeRequestMsg request message.
     *
     * @param token  the token that identifies the client
     */
    public EscapeRequestMsg(Token token) {

        super(token);

    }

    @Override
    public Action createAction(ActionFactoryVisitor visitor) {

        return visitor.visit(this);

    }

}
