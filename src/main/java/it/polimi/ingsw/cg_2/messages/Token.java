package it.polimi.ingsw.cg_2.messages;

import com.google.common.base.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return Objects.equal(uuid, token.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }

}
