package it.polimi.ingsw.cg_2.messages.responses;

/**
 * A response message for the EscapeAction. Contains information about the
 * type of the hatch card, a confirmation about the position and the next
 * player
 * that will take the turn (if the card was GREEN and the player escaped).
 */
public class EscapeResponseMsg implements ResponseMsg {

    private final String cardType;
    private final String position;
    private final int newTurn;
    private final int nextPlayer;

    /**
     * Creates a new EscapeResponseMsg.
     *
     * @param cardType   the type of hatch card
     * @param position   the position of the hatch
     * @param nextPlayer the next player that will take the turn (if the card
     *                   was GREEN and the player escaped).
     */
    public EscapeResponseMsg(String cardType, String position, int newTurn,
            int nextPlayer) {

        this.cardType = cardType;
        this.position = position;
        this.newTurn = newTurn
        this.nextPlayer = nextPlayer;

    }

    /**
     * Get the hatch card type.
     *
     * @return the hatch card type
     */
    public String getCardType() {

        return cardType;

    }

    /**
     * Get the position of the hatch sector.
     *
     * @return the position of the hatch sector
     */
    public String getPosition() {

        return position;

    }

    /**
     * Get the next turn number.
     *
     * @return the new turn number, -1 if the card was GREEN
     */
    public int getNewTurn() {

        return newTurn;

    }

    /**
     * Get the next player that will take the turn.
     *
     * @return the number of the next player, -1 of the card was GREEN
     */
    public int getNextPlayer() {

        return nextPlayer;

    }

}
