package it.polimi.ingsw.cg_2.model.deck;

/**
 * This class implements a (@link Card) to represent an Item Card.
 */
public class HatchCard implements Card {

    /**
     * This enumeration contains the possible types of HatchCard, it implements
     * CardType so the type can be accessed polymorphically.
     */
    public enum HatchCardType implements CardType {
        RED, GREEN;
    }

    private final HatchCardType type;

    /**
     * Instantiates a new Hatch Card.
     *
     * @param type the type of the new card
     */
    public HatchCard(HatchCardType type) {
        this.type = type;
    }

    @Override
    public HatchCardType getType() {
        return type;
    }

}
