package it.polimi.ingsw.cg_2.messages.requests.actions;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.controller.actions.ActionCreator;
import it.polimi.ingsw.cg_2.controller.actions.ActionFactoryVisitor;
import it.polimi.ingsw.cg_2.messages.Token;
import it.polimi.ingsw.cg_2.messages.requests.RequestMsg;

/**
 * This is the class for request messages from the client to the server that
 * involves performing an action on the game. Subclasses must implement the
 * {@link ActionCreator} interface so the correct Action can be created
 * polymorphically without knowing the type of request message thanks to the
 * visitor pattern.
 */
public abstract class ActionRequestMsg extends RequestMsg implements
        ActionCreator {

    /**
     * The constructor for this abstract class, a token used to identify the
     * client is passed to the superclass.
     *
     * @param token the token that identifies a client
     */
    public ActionRequestMsg(Token token) {

        super(token);

    }

    @Override
    public abstract Action createAction(ActionFactoryVisitor visitor);

}
