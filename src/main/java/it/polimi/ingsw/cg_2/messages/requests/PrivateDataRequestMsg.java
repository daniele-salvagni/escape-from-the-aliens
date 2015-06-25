package it.polimi.ingsw.cg_2.messages.requests;

/**
 *
 */
public class PrivateDataRequestMsg {

    private final String race;
    private final String rank;
    private final int playerNumber;

    public PrivateDataRequestMsg(String race, String rank, int playerNumber) {

        this.race = race;
        this.rank = rank;
        this.playerNumber = playerNumber;

    }

    public String getRace() {

        return race;

    }

    public String getRank() {

        return rank;

    }

    public int getPlayerNumber() {

        return playerNumber;

    }

}
