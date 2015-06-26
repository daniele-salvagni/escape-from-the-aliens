package it.polimi.ingsw.cg_2.messages.broadcast;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

import java.util.HashMap;

/**
 *
 */
public class GameFinishedBroadcastMsg implements BroadcastMsg {

    private final HashMap<Integer, String> winners;
    private final HashMap<Integer, String> losers;

    public GameFinishedBroadcastMsg(HashMap<Integer, String> winners, HashMap<Integer,
            String> losers) {

        this.winners = winners;
        this.losers = losers;

    }

    public HashMap<Integer, String> getWinners() {

        return winners;

    }

    public HashMap<Integer, String> getLosers() {

        return losers;

    }

    @Override
    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
