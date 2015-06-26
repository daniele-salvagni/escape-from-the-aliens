package it.polimi.ingsw.cg_2.messages.responses.actions;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class UseAtkItemResponseMsg extends ActionResponseMsg {

    private final String coordinate;
    private final HashMap<Integer, String> kills;
    private final ArrayList<Integer> survivors;

    public UseAtkItemResponseMsg(boolean success, String coordinate,
            Map<Integer, String> kills, List<Integer> survivors) {

        super(success);

        this.coordinate = coordinate;
        this.kills = new HashMap<>(kills);
        this.survivors = new ArrayList<>(survivors);

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

    @Override
    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
