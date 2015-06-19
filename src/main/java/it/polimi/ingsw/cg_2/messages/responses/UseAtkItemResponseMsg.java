package it.polimi.ingsw.cg_2.messages.responses;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class UseAtkItemResponseMsg implements ResponseMsg {

    private final String coordinate;
    private final Map<Integer, String> kills;
    private final List<Integer> survivors;

    public UseAtkItemResponseMsg(String coordinate, Map<Integer, String>
            kills, List<Integer> survivors) {

        this.coordinate = coordinate;
        this.kills = kills;
        this.survivors = survivors;

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
