package it.polimi.ingsw.cg_2.messages.responses;

import java.util.Map;

/**
 *
 */
public class UseSptItemResponseMsg {

    private final String coordinate;
    private final Map<Integer, String> spottedPlayers;

    public UseSptItemResponseMsg(String coordinate, Map<Integer, String>
            spottedPlayers) {

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
