package it.polimi.ingsw.cg_2.messages.requests;

import it.polimi.ingsw.cg_2.messages.Token;

/**
 * A message ot request the public log of a game.
 */
public class PublicLogRequestMsg extends RequestMsg {

    /**
     * Create a new PublicLogRequestMsg request message.
     *
     * @param token the token that identifies the client
     */
    public PublicLogRequestMsg(Token token) {

        super(token);
    }

}
