package it.polimi.ingsw.cg_2.messages.responses.actions;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class UseSptItemResponseMsg extends ActionResponseMsg {

    private final String coordinate;
    private final HashMap<Integer, String> spottedPlayers;

    public UseSptItemResponseMsg(boolean success, String coordinate,
                Map<Integer, String> spottedPlayers) {

        super(success);

        this.coordinate = coordinate;
        this.spottedPlayers = new HashMap<>(spottedPlayers);

    }

    public String getCoordinate() {

        return coordinate;

    }

    public HashMap<Integer, String> getSpottedPlayers() {

        return spottedPlayers;

    }

}
