package it.polimi.ingsw.cg_2.messages.responses;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class PrivateDataResponseMsg implements ResponseMsg {

    private final String race;
    private final String rank;
    private final int playerNumber;
    private final String position;
    private final ArrayList<String> heldItems;
    private final ArrayList<String> activatedItems;

    public PrivateDataResponseMsg(String race, String rank, int playerNumber, String
            position, List<String> heldItems, List<String> activatedItems) {

        this.race = race;
        this.rank = rank;
        this.playerNumber = playerNumber;
        this.position = position;
        this.heldItems = new ArrayList<>(heldItems);
        this.activatedItems = new ArrayList<>(activatedItems);

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

    public String getPosition() {

        return position;

    }

    public List<String> getHeldItems() {

        return heldItems;

    }

    public List<String> getActivatedItems() {

        return activatedItems;

    }

    @Override
    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
