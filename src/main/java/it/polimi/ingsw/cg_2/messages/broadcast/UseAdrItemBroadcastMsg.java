package it.polimi.ingsw.cg_2.messages.broadcast;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

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

    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
