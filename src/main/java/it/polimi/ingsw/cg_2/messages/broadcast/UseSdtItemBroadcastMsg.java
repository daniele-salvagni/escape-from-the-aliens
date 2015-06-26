package it.polimi.ingsw.cg_2.messages.broadcast;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

/**
 * Message to broadcast the result of a player using a SEDATIVES item.
 */
public class UseSdtItemBroadcastMsg implements BroadcastMsg {

    private final int player;

    /**
     * Create a new UseSdtItemBroadcastMsg.
     *
     * @param player the number of the player using the item
     */
    public UseSdtItemBroadcastMsg(int player) {

        this.player = player;

    }

    /**
     * Get the number of the player using the item.
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
