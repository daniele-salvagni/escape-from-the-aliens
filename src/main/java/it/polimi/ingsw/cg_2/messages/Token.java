package it.polimi.ingsw.cg_2.messages;

import java.io.Serializable;

/**
 *
 */
public class Token implements Serializable {

    private final String uuid;

    public Token(String uuid) {

        this.uuid = uuid;

    }

    public String getUuid() {

        return uuid;

    }

}
