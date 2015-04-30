package it.polimi.ingsw.cg_2.model.deck;

/**
 * This interface represents a generic Card and can be used to retrieve the type
 * of a card polymorphically.
 */
public interface Card {

    /**
     * This interface is used to categorize all the Card Types, it is nested in
     * the (@link Card) class to avoid confusion. In this way we could treat
     * every kind of card in the same way.
     * <p>
     * For example if we have two different item card decks with different kinds
     * of cards each and we want to check the card type we can do it
     * polymorphically thanks to this interface (enumerations can't be
     * extended).
     * <p>
     * CardType enumerations in child classes must implement CardType. It is
     * declared <b>static</b> since there is no need to access members of the
     * enclosing class.
     */
    public static interface CardType {
        // This is deliberately empty.
    }

    /**
     * Gets the type of the card.
     *
     * @return the type of the card
     */
    public CardType getType();

}
