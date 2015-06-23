package it.polimi.ingsw.cg_2.messages.requests;

import it.polimi.ingsw.cg_2.messages.Token;

/**
 *
 */
public class SubscribeRequestMsg extends RequestMsg {

    private final String topic;

    public SubscribeRequestMsg(Token token, String topic) {

        super(token);
        this.topic = topic;

    }

    public String getTopic() {

        return topic;

    }

}
