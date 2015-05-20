package it.polimi.ingsw.cg_2.model.deck;

/**
 * This class implements a (@link Card) to represent an Item Card.
 */
public class ItemCard implements Card {

    /**
     * This enumeration contains the possible types of ItemCard, it implements
     * CardType so the type can be accessed polymorphically.
     */
    public enum ItemCardType implements CardType {
        ATTACK,
        TELEPORT,
        SEDATIVES,
        SPOTLIGHT,
        DEFENSE,
        ADRENALINE;
    }

    private final ItemCardType type;

    /**
     * Instantiates a new Item Card.
     *
     * @param type the type of the new card
     */
    public ItemCard(ItemCardType type) {

        this.type = type;

    }

    @Override
    public ItemCardType getType() {

        return type;

    }

}
