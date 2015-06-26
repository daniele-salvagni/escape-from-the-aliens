package it.polimi.ingsw.cg_2.messages.requests;

import it.polimi.ingsw.cg_2.messages.Token;

/**
 * A message to request private data about the player.
 */
public class PrivateDataRequestMsg extends RequestMsg {

    /**
     * Create a new PrivateDateRequestMsg message.
     *
     * @param token the token that identifies the client
     */
    public PrivateDataRequestMsg(Token token) {

        super(token);

    }

}
