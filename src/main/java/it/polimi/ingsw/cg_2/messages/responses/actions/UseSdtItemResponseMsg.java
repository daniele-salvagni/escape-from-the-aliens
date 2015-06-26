package it.polimi.ingsw.cg_2.messages.responses.actions;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

/**
 *
 */
public class UseSdtItemResponseMsg extends ActionResponseMsg {

    public UseSdtItemResponseMsg(boolean success) {

        super(success);

    }

    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
