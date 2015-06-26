package it.polimi.ingsw.cg_2.messages.broadcast;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

/**
 * Message to broadcast the start of a new turn.
 */
public class PassBroadcastMsg implements BroadcastMsg {

    private final int player;
    private final int newTurn;
    private final int newPlayer;

    /**
     * Create a new PassBroadcastMsg.
     *
     * @param player the number of the player passing the turn
     * @param newTurn the new turn number
     * @param newPlayer the number of the new player playing the turn
     */
    public PassBroadcastMsg(int player, int newTurn, int newPlayer) {

        this.player = player;
        this.newTurn = newTurn;
        this.newPlayer = newPlayer;

    }

    /**
     * Get the number of the player passing the turn.
     *
     * @return the number of the player passing the turn
     */
    public int getPlayer() {

        return player;

    }

    /**
     * Get the new turn number.
     *
     * @return the new turn number
     */
    public int getNewTurn() {

        return newTurn;

    }

    /**
     * Get the number of the new player playing the turn.
     *
     * @return  the number of the new player playing the turn
     */
    public int getNewPlayer() {

        return newPlayer;

    }

    @Override
    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
