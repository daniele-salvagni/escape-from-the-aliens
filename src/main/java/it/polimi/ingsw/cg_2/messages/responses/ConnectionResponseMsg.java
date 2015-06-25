package it.polimi.ingsw.cg_2.messages.responses;

import it.polimi.ingsw.cg_2.messages.Token;
import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

/**
 *
 */
public class ConnectionResponseMsg implements ResponseMsg {

    private final Token token;

    public ConnectionResponseMsg(Token token) {

        this.token = token;

    }

    public Token getToken() {

        return token;

    }

    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
