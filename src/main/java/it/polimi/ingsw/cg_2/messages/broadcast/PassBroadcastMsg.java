package it.polimi.ingsw.cg_2.messages.broadcast;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

/**
 *
 */
public class PassBroadcastMsg implements BroadcastMsg {

    private final int player;
    private final int newTurn;
    private final int newPlayer;

    public PassBroadcastMsg(int player, int newTurn, int newPlayer) {

        this.player = player;
        this.newTurn = newTurn;
        this.newPlayer = newPlayer;

    }

    public int getPlayer() {

        return player;

    }

    public int getNewTurn() {

        return newTurn;

    }

    public int getNewPlayer() {

        return newPlayer;

    }

    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
