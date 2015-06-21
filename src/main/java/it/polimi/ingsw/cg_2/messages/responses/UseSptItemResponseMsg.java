package it.polimi.ingsw.cg_2.messages.responses;

import java.util.Map;

/**
 *
 */
public class UseSptItemResponseMsg extends ActionResponseMsg {

    private final String coordinate;
    private final Map<Integer, String> spottedPlayers;

    public UseSptItemResponseMsg(boolean success, String coordinate,
                Map<Integer, String> spottedPlayers) {

        super(success);

        this.coordinate = coordinate;
        this.spottedPlayers = spottedPlayers;

    }

    public String getCoordinate() {

        return coordinate;

    }

    public Map<Integer, String> getSpottedPlayers() {

        return spottedPlayers;

    }

}
