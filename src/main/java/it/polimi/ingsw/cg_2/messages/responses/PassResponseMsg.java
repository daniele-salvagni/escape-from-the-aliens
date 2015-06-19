package it.polimi.ingsw.cg_2.messages.responses;

/**
 * A response message for the PassAction. Contains only a confirmation by
 * sending the new turn number.
 */
public class PassResponseMsg implements ResponseMsg {

    private final int newTurn;
    private final int nextPlayer;

    /**
     * Create a new PassResponseMsg.
     *
     * @param newTurn the new turn number
     * @param nextPlayer the next player that takes the turn
     */
    public PassResponseMsg(int newTurn, int nextPlayer) {

        this.newTurn = newTurn;
        this.nextPlayer = nextPlayer;

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
     * Get the next player.
     *
     * @return the new player that takes the turn
     */
    public int getNextPlayer() {

        return nextPlayer;

    }
}
