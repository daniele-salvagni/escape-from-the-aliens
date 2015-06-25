package it.polimi.ingsw.cg_2.messages.requests;

import it.polimi.ingsw.cg_2.messages.Token;

/**
 *
 */
public class ChangeMapRequestMsg extends RequestMsg {

    private final String map;

    public ChangeMapRequestMsg(Token token, String map) {

        super(token);
        this.map = map;

    }

    public String getMap() {

        return map;

    }

}
