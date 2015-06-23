package it.polimi.ingsw.cg_2.messages.responses;

/**
 *
 */
public class SubscribeResponseMsg implements ResponseMsg {

    private final String topic;

    public SubscribeResponseMsg(String topic) {

        this.topic = topic;

    }

    public String getTopic() {

        return topic;

    }

}
