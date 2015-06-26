package it.polimi.ingsw.cg_2.messages.responses;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

/**
 *
 */
public class SubscribeResponseMsg implements ResponseMsg {

    private final boolean success;

    public SubscribeResponseMsg(boolean success) {

        this.success = success;

    }

    public boolean isSuccess() {

        return success;

    }

    @Override
    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
