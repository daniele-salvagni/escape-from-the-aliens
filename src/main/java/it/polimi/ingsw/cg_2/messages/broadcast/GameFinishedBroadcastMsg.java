package it.polimi.ingsw.cg_2.messages.broadcast;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

import java.util.HashMap;
import java.util.Map;

/**
 * A message to broadcast the ending of a game.
 */
public class GameFinishedBroadcastMsg implements BroadcastMsg {

    private final HashMap<Integer, String> winners;
    private final HashMap<Integer, String> losers;

    /**
     * Create a new GameFinishedBroadcastMsg.
     *
     * @param winners the number and the race of the winners
     * @param losers the number and he race of the losers
     */
    public GameFinishedBroadcastMsg(Map<Integer, String> winners, Map<Integer,
            String> losers) {

        this.winners = new HashMap<>(winners);
        this.losers = new HashMap<>(losers);

    }

    /**
     * Get the number and the race of the winners.
     *
     * @return the number and the race of the winners
     */
    public Map<Integer, String> getWinners() {

        return winners;

    }

    /**
     * Get the number and he race of the losers.
     *
     * @return the number and he race of the losers
     */
    public Map<Integer, String> getLosers() {

        return losers;

    }

    @Override
    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
