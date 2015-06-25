package it.polimi.ingsw.cg_2.messages.requests.actions;

import it.polimi.ingsw.cg_2.messages.Token;
import it.polimi.ingsw.cg_2.messages.requests.RequestMsg;

/**
 *
 */
public class SendChatMsg extends RequestMsg {

    private final String message;

    public SendChatMsg(Token token, String message) {

        super(token);
        this.message = message;

    }

    public String getMessage() {

        return message;

    }

}
