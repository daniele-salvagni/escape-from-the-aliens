package it.polimi.ingsw.cg_2.messages.responses;

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

}
