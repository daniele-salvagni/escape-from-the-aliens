package it.polimi.ingsw.cg_2.messages.broadcast;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

import java.util.HashMap;
import java.util.Map;

/**
 * Message to broadcast the result of a player using a SPOTLIGHT item.
 */
public class UseSptItemBroadcastMsg implements BroadcastMsg {

    private final int player;
    private final String coordinate;
    private final HashMap<Integer, String> spottedPlayers;

    /**
     * Create a new UseSptItemBroadcastMsg.
     *
     * @param player         the number of the player using the item
     * @param coordinate     the coordinate of the spotlight in the COL:ROW format
     * @param spottedPlayers the numbers of the players spotted alongside their position
     */
    public UseSptItemBroadcastMsg(int player, String coordinate, Map<Integer,
            String> spottedPlayers) {

        this.player = player;
        this.coordinate = coordinate;
        this.spottedPlayers = new HashMap<>(spottedPlayers);

    }

    /**
     * Get the number of the player using the item.
     *
     * @return the number of the player using the item
     */
    public int getPlayer() {

        return player;

    }

    /**
     * Get the coordinate of the spotlight in the COL:ROW format.
     *
     * @return the coordinate of the spotlight in the COL:ROW format
     */
    public String getCoordinate() {

        return coordinate;

    }

    /**
     * Get the numbers of the players spotted alongside their position
     *
     * @return the numbers of the players spotted alongside their position
     */
    public Map<Integer, String> getSpottedPlayers() {

        return spottedPlayers;

    }

    @Override
    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
