package it.polimi.ingsw.cg_2.messages.broadcast;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A message to broadcast the result of an attack action.
 */
public class AttackBroadcastMsg implements BroadcastMsg {

    private final int player;
    private final String coordinate;
    private final HashMap<Integer, String> kills;
    private final ArrayList<Integer> survivors;

    /**
     * Create a new AttackBroadcastMsg.
     *
     * @param player the number of the player that attacked
     * @param coordinate the coordinate of the attack in the COL:ROW format
     * @param kills the numbers of the players killed
     * @param survivors the numbers of the players that used DEFENSE to survive
     */
    public AttackBroadcastMsg(int player, String coordinate, Map<Integer,
            String> kills, List<Integer> survivors) {

        this.player = player;
        this.coordinate = coordinate;
        this.kills = new HashMap<>(kills);
        this.survivors = new ArrayList<>(survivors);

    }

    /**
     * Get the number of the player that attacked.
     *
     * @return the number of the player that attacked
     */
    public int getPlayer() {

        return player;

    }

    /**
     * Get  the coordinate of the attack in the COL:ROW format.
     *
     * @return the coordinate of the attack in the COL:ROW format
     */
    public String getCoordinate() {

        return coordinate;

    }

    /**
     * Get the numbers of the players killed.
     *
     * @return the numbers of the players killed
     */
    public Map<Integer, String> getKills() {

        return kills;

    }

    /**
     * Get the numbers of the players that used DEFENSE to survive.
     *
     * @return the numbers of the players that used DEFENSE to survive
     */
    public List<Integer> getSurvivors() {

        return survivors;

    }

    @Override
    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
