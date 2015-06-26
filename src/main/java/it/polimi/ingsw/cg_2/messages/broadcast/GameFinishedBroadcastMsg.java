package it.polimi.ingsw.cg_2.messages.broadcast;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class GameFinishedBroadcastMsg implements BroadcastMsg {

    private final HashMap<Integer, String> winners;
    private final HashMap<Integer, String> losers;

    public GameFinishedBroadcastMsg(Map<Integer, String> winners, Map<Integer,
            String> losers) {

        this.winners = new HashMap<>(winners);
        this.losers = new HashMap<>(losers);

    }

    public Map<Integer, String> getWinners() {

        return winners;

    }

    public Map<Integer, String> getLosers() {

        return losers;

    }

    @Override
    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
