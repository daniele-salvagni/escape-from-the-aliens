package it.polimi.ingsw.cg_2.model.deck;

/**
 * An abstract factory for creating Decks, it can be implemented to create decks
 * with a certain number of cards depending on the game mode.
 */
public interface DecksFactory {

    /**
     * Creates a populated (@link Deck) of (@link ItemCard).
     *
     * @return a new (@link Deck) of (@link ItemCard).
     */
    Deck<ItemCard> createItemDeck();

    /**
     * Creates a populated (@link Deck) of (@link SectorCard).
     *
     * @return a new (@link Deck) of (@link SectorCard).
     */
    Deck<SectorCard> createSectorDeck();

    /**
     * Creates a populated (@link Deck) of (@link HatchCard).
     *
     * @return a new (@link Deck) of (@link HatchCard).
     */
    Deck<HatchCard> createHatchDeck();
}
