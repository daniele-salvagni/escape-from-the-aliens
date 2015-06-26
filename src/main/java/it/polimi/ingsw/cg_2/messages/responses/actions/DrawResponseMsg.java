package it.polimi.ingsw.cg_2.messages.responses.actions;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

/**
 * A response message for the DrawAction. Contains information about the
 * type of the sector card and the item found (if any).
 */
public class DrawResponseMsg extends ActionResponseMsg {

    private final String cardType;
    private final String itemType;

    /**
     * Creates a new DrawResponseMsg.
     *
     * @param cardType the type of the drawn card
     * @param itemType the type of the item found (can be null)
     */
    public DrawResponseMsg(boolean success, String cardType, String itemType) {

        super(success);

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

    @Override
    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
