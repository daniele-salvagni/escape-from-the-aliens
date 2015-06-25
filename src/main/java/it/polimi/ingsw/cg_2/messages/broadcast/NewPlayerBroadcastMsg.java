package it.polimi.ingsw.cg_2.messages.broadcast;

/**
 *
 */
public class NewPlayerBroadcastMsg implements BroadcastMsg {

    private final int playerNumber;

    public NewPlayerBroadcastMsg(int playerNumber) {

        this.playerNumber = playerNumber;

    }

    public int getPlayerNumber() {

        return playerNumber;

    }

}
