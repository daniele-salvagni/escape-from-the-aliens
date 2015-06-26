package it.polimi.ingsw.cg_2.messages.responses.actions;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

/**
 *
 */
public class UseAdrItemResponseMsg extends ActionResponseMsg {

    public UseAdrItemResponseMsg(boolean success) {

        super(success);

    }

    @Override
    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
