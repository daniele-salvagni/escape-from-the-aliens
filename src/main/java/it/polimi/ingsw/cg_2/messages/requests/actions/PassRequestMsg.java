package it.polimi.ingsw.cg_2.messages.requests.actions;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.controller.actions.ActionFactoryVisitor;
import it.polimi.ingsw.cg_2.messages.Token;

/**
 * Message to request to pass the turn to a new player.
 */
public class PassRequestMsg extends ActionRequestMsg {

    /**
     * Create a new PassRequestMsg request message.
     *
     * @param token the token that identifies the client
     */
    public PassRequestMsg(Token token) {

        super(token);

    }

    @Override
    public Action createAction(ActionFactoryVisitor visitor) {

        return visitor.visit(this);

    }

}
