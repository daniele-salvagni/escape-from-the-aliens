package it.polimi.ingsw.cg_2.messages.requests.actions;

import it.polimi.ingsw.cg_2.messages.Token;
import it.polimi.ingsw.cg_2.messages.requests.RequestMsg;

/**
 * A message to send a text message to the other players.
 */
public class SendChatMsg extends RequestMsg {

    private final String message;

    /**
     * Create a new SendChatMsg request message.
     *
     * @param token the token that identifies the client
     * @param message
     */
    public SendChatMsg(Token token, String message) {

        super(token);
        this.message = message;

    }

    /**
     * Get the text message.
     *
     * @return the text message
     */
    public String getMessage() {

        return message;

    }

}
