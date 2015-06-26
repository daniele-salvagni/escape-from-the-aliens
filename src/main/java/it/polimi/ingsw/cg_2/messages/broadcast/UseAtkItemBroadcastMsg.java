package it.polimi.ingsw.cg_2.messages.broadcast;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Message to broadcast the result of a player using an ATTACK item.
 */
public class UseAtkItemBroadcastMsg implements BroadcastMsg {

    private final String coordinate;
    private final HashMap<Integer, String> kills;
    private final ArrayList<Integer> survivors;
    private final int player;

    /**
     * Create a new UseAtkItemBroadcastMsg.
     *
     * @param player     the number of the player using the item
     * @param coordinate the coordinate of the attack
     * @param kills      the numbers of the players killed
     * @param survivors  the numbers of the players survived using DEFENSE
     */
    public UseAtkItemBroadcastMsg(int player, String coordinate, Map<Integer,
            String> kills, List<Integer> survivors) {

        this.coordinate = coordinate;
        this.kills = new HashMap<>(kills);
        this.survivors = new ArrayList<>(survivors);
        this.player = player;

    }

    /**
     * Get the coordinate of the attack.
     *
     * @return the coordinate of the attack
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
     * Get the numbers of the players survived using DEFENSE.
     *
     * @return the numbers of the players survived using DEFENSE
     */
    public List<Integer> getSurvivors() {

        return survivors;

    }

    /**
     * Get the the number of the player using the item.
     *
     * @return the number of the player using the item
     */
    public int getPlayer() {

        return player;

    }

    @Override
    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
