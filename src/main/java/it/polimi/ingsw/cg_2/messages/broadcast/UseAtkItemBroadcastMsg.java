package it.polimi.ingsw.cg_2.messages.broadcast;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class UseAtkItemBroadcastMsg implements BroadcastMsg {

    private final String coordinate;
    private final HashMap<Integer, String> kills;
    private final ArrayList<Integer> survivors;
    private final int player;

    public UseAtkItemBroadcastMsg(int player, String coordinate, Map<Integer,
            String> kills, List<Integer> survivors) {

        this.coordinate = coordinate;
        this.kills = new HashMap<>(kills);
        this.survivors = new ArrayList<>(survivors);
        this.player = player;

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

    public int getPlayer() {

        return player;

    }

    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
