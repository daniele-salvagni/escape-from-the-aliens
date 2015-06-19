package it.polimi.ingsw.cg_2.messages.broadcast;

/**
 *
 */
public class UseSdtItemBroadcastMsg implements BroadcastMsg {

    private final int player;

    public UseSdtItemBroadcastMsg(int player) {

        this.player = player;

    }

    public int getPlayer() {

        return player;

    }

}
