package it.polimi.ingsw.cg_2.messages.broadcast;

/**
 *
 */
public class UseAdrItemBroadcastMsg implements BroadcastMsg {

    private final int player;

    public UseAdrItemBroadcastMsg(int player) {

        this.player = player;

    }

    public int getPlayer() {

        return player;

    }

}
