package it.polimi.ingsw.cg_2.messages.broadcast;

import java.util.Map;

/**
 *
 */
public class UseSptItemBroadcastMsg implements BroadcastMsg {

    private final int player;
    private final String coordinate;
    private final Map<Integer, String> spottedPlayers;

    public UseSptItemBroadcastMsg(int player, String coordinate, Map<Integer,
            String> spottedPlayers) {

        this.player = player;
        this.coordinate = coordinate;
        this.spottedPlayers = spottedPlayers;

    }

    public int getPlayer() {

        return player;

    }

    public String getCoordinate() {

        return coordinate;

    }

    public Map<Integer, String> getSpottedPlayers() {

        return spottedPlayers;

    }

}
