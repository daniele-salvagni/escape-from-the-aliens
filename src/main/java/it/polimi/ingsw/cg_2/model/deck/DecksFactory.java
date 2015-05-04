package it.polimi.ingsw.cg_2.model.deck;

/**
 * An abstract factory for creating Decks, it can be implemented to create decks
 * with a certain number of cards depending on the game mode.
 */
public interface DecksFactory {

    /**
     * Creates a populated (@link DeckManager) of (@link ItemCard).
     *
     * @return a new (@link DeckManager) of (@link ItemCard).
     */
    DeckManager<ItemCard> createItemDeck();

    /**
     * Creates a populated (@link DeckManager) of (@link SectorCard).
     *
     * @return a new (@link DeckManager) of (@link SectorCard).
     */
    DeckManager<SectorCard> createSectorDeck();

    /**
     * Creates a populated (@link DeckManager) of (@link HatchCard).
     *
     * @return a new (@link DeckManager) of (@link HatchCard).
     */
    DeckManager<HatchCard> createHatchDeck();
}
