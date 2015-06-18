package it.polimi.ingsw.cg_2.messages.responses;

/**
 * A response message for the PassAction. Contains only a confirmation by
 * sending the new turn number.
 */
public class PassResponseMsg implements ResponseMsg {

    private final int newTurn;

    /**
     * Create a new PassResponseMsg.
     *
     * @param newTurn the new turn number
     */
    public PassResponseMsg(int newTurn) {

        this.newTurn = newTurn;

    }

    /**
     * Get the new turn number
     *
     * @return the new turn number
     */
    public int getNewTurn() {

        return newTurn;

    }

}
