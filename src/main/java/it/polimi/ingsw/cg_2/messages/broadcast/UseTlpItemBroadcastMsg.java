package it.polimi.ingsw.cg_2.messages.broadcast;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

/**
 * Message to broadcast the result of a player using a TELEPORT item.
 */
public class UseTlpItemBroadcastMsg implements BroadcastMsg {

    private final int player;
    private final String coordinate;

    /**
     * Create a new UseTlpItemBroadcastMsg.
     *
     * @param player the number of the player using the item
     * @param coordinate the coordinate where the player teleported (human/alien sector)
     */
    public UseTlpItemBroadcastMsg(int player, String coordinate) {

        this.player = player;
        this.coordinate = coordinate;

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
     * Get the coordinate where the player teleported (human/alien sector).
     *
     * @return the coordinate where the player teleported (human/alien sector)
     */
    public String getCoordinate() {

        return coordinate;

    }

    @Override
    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
