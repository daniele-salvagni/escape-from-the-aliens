package it.polimi.ingsw.cg_2.messages.broadcast;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class AttackBroadcastMsg implements BroadcastMsg {

    private final int player;
    private final String coordinate;
    private final Map<Integer, String> kills;
    private final List<Integer> survivors;

    public AttackBroadcastMsg(int player, String coordinate, Map<Integer,
            String> kills, List<Integer> survivors) {

        this.player = player;
        this.coordinate = coordinate;
        this.kills = kills;
        this.survivors = survivors;

    }

    public int getPlayer() {

        return player;

    }

    public String getCoordinate() {

        return coordinate;

    }

    public Map<Integer, String> getKills() {

        return kills;

    }

    public List<Integer> getSurvivors() {

        return survivors;

    }

}
