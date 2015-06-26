package it.polimi.ingsw.cg_2.messages.requests;

import it.polimi.ingsw.cg_2.messages.Token;

/**
 * A message to request the log of public events of the game.
 */
public class LogRequestMsg extends RequestMsg{

    /**
     * Create a new logRequestMsg reqeust message.
     *
     * @param token the token that identifies the client
     */
    public LogRequestMsg(Token token) {

        super(token);

    }

}
