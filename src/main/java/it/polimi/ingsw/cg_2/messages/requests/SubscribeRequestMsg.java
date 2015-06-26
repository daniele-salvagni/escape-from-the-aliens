package it.polimi.ingsw.cg_2.messages.requests;

import it.polimi.ingsw.cg_2.messages.Token;

/**
 * A message to request to be registered to the broker.
 */
public class SubscribeRequestMsg extends RequestMsg {

    /**
     * Create a new SubscribeRequestMsg request message
     *
     * @param token the token that identifies the client
     */
    public SubscribeRequestMsg(Token token) {

        super(token);

    }

}
