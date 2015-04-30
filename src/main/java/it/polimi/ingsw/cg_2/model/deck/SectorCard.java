package it.polimi.ingsw.cg_2.model.deck;

/**
 * This class implements a (@link Card) to represent an Sector Card.
 */
public class SectorCard implements Card {

    /**
     * This enumeration contains the possible types of SectorCard, it implements
     * CardType so the type can be accessed polymorphically.
     */
    public enum SectorCardType implements CardType {
        NOISE, DECEPTION, SILENCE;
    }

    SectorCardType type;
    boolean containsItem;

    /**
     * Instantiates a new Sector Card without an item.
     *
     * @param type the type of the new card
     */
    public SectorCard(SectorCardType type) {
        this.type = type;
        this.containsItem = false;
    }

    /**
     * Instantiates a new Sector Card.
     *
     * @param type the type of the new card
     * @param containsItem true if this card should contain an item
     */
    public SectorCard(SectorCardType type, boolean containsItem) {
        this.type = type;
        this.containsItem = containsItem;
    }

    /**
     * Checks if this card contains an item or not.
     *
     * @return true, if this card contains an item
     */
    public boolean containsItem() {
        return containsItem;
    }

    @Override
    public SectorCardType getType() {
        return type;
    }

}
