package it.polimi.ingsw.cg_2.messages.responses;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

/**
 *
 */
public class PrivateDataResponseMsg implements ResponseMsg {

    private final String race;
    private final String rank;
    private final int playerNumber;

    public PrivateDataResponseMsg(String race, String rank, int playerNumber) {

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

    @Override
    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
