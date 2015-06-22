package it.polimi.ingsw.cg_2.messages.responses;

import it.polimi.ingsw.cg_2.messages.Token;

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

}
