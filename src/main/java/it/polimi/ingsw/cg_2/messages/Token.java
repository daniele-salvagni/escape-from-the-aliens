package it.polimi.ingsw.cg_2.messages;

import com.google.common.base.Objects;

import java.io.Serializable;

/**
 * A unique token used to identify a specific client, it can contain additional
 * information but only the uuid is used for identification.
 */
public class Token implements Serializable {

    private final String uuid;
    // Player number is optional
    private int playerNumber;

    /**
     * Create a new Token.
     *
     * @param uuid an unique identifier in String format
     */
    public Token(String uuid) {

        this.uuid = uuid;
        playerNumber = -1;

    }

    /**
     * Get the unique identifier of the client.
     *
     * @return the unique identifier of the client
     */
    public String getUuid() {

        return uuid;

    }

    /**
     * Get the player number in a game.
     *
     * @return the player number in a game
     */
    public int getPlayerNumber() {

        return playerNumber;

    }

    /**
     * Set the player number in a game.
     *
     * @param playerNumber the player number in a game
     */
    public void setPlayerNumber(int playerNumber) {

        this.playerNumber = playerNumber;

    }

    @Override
    public boolean equals(Object o) {

        // NOTE: Only uuid is used!

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return Objects.equal(uuid, token.uuid);

    }

    @Override
    public int hashCode() {

        // NOTE: Only uuid is used!
        return Objects.hashCode(uuid);

    }

}
