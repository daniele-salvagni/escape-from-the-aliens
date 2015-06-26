package it.polimi.ingsw.cg_2.messages.broadcast;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

/**
 * A message to broadcast the result of an escape action.
 */
public class EscapeBroadcastMsg implements BroadcastMsg {

    private final int player;
    private final String cardType;
    private final String coordinate;
    private final int newTurn;
    private final int nextPlayer;

    /**
     * Create a new EscapeBroadcastMsg action.
     *
     * @param player the number of the player that performed the action
     * @param cardType the card type found when trying to escape (RED or GREEN)
     * @param coordinate the coordinate of the hatch sector in the COL:ROW format
     * @param newTurn the number of the new turn (if the player escaped)
     * @param nextPlayer the number of the next player (if the player escaped)
     */
    public EscapeBroadcastMsg(int player, String cardType, String coordinate,
            int newTurn, int nextPlayer) {

        this.player = player;
        this.cardType = cardType;
        this.coordinate = coordinate;
        this.newTurn = newTurn;
        this.nextPlayer = nextPlayer;

    }

    /**
     * Get the number of the player that performed the action.
     *
     * @return the number of the player that performed the action
     */
    public int getPlayer() {

        return player;

    }

    /**
     * Get the card type found when trying to escape (RED or GREEN).
     *
     * @return  the card type found when trying to escape (RED or GREEN)
     */
    public String getCardType() {

        return cardType;

    }

    /**
     * Get the coordinate of the hatch sector in the COL:ROW format.
     *
     * @return the coordinate of the hatch sector in the COL:ROW format
     */
    public String getCoordinate() {

        return coordinate;

    }

    /**
     * Get the number of the new turn (if the player escaped).
     *
     * @return the number of the new turn (if the player escaped)
     */
    public int getNewTurn() {

        return newTurn;

    }

    /**
     * Get the number of the next player (if the player escaped).
     *
     * @return the number of the next player (if the player escaped)
     */
    public int getNextPlayer() {

        return nextPlayer;

    }

    @Override
    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
