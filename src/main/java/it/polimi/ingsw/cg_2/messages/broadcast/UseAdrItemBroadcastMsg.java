package it.polimi.ingsw.cg_2.messages.broadcast;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

/**
 * Message to broadcast the result of a player using an ADRENALINE item.
 */
public class UseAdrItemBroadcastMsg implements BroadcastMsg {

    private final int player;

    /**
     * Create a new UseAdrItemBroadcastMsg.
     *
     * @param player the number of the player using the item
     */
    public UseAdrItemBroadcastMsg(int player) {

        this.player = player;

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
