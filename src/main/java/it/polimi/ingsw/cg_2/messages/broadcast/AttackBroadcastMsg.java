package it.polimi.ingsw.cg_2.messages.broadcast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class AttackBroadcastMsg implements BroadcastMsg {

    private final int player;
    private final String coordinate;
    private final HashMap<Integer, String> kills;
    private final ArrayList<Integer> survivors;

    public AttackBroadcastMsg(int player, String coordinate, Map<Integer,
            String> kills, List<Integer> survivors) {

        this.player = player;
        this.coordinate = coordinate;
        this.kills = new HashMap<>(kills);
        this.survivors = new ArrayList<>(survivors);

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
