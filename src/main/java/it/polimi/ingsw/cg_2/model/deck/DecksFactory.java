package it.polimi.ingsw.cg_2.model.deck;

/**
 * This interface is for creating the three decks of cards for this game, it
 * delegates the responsibility to its subclasses to properly populate the
 * Decks.
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
