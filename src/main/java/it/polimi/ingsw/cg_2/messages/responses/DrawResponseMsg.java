package it.polimi.ingsw.cg_2.messages.responses;

/**
 * A response message for the DrawAction. Contains information about the
 * type of the sector card and the item found (if any).
 */
public class DrawResponseMsg {

    private final String cardType;
    private final String itemType;

    /**
     * Creates a new DrawResponseMsg.
     *
     * @param cardType the type of the drawn card
     */
    public DrawResponseMsg(String cardType, String itemType) {

        if (cardType == null) {
            throw new IllegalArgumentException("cardType cannot be null.");
        }

        this.cardType = cardType;
        this.itemType = itemType;

    }

    /**
     * Get the type of the sector card found
     *
     * @return the type of the sector card found
     */
    public String getCardType() {

        return cardType;

    }

    /**
     * Get the type of the item card found (if any, otherwise is null)
     *
     * @return the type of the item card found
     */
    public String getItemType() {

        return itemType;
    }
}