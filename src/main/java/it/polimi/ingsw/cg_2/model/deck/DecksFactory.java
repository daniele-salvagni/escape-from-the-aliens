package it.polimi.ingsw.cg_2.model.deck;

import it.polimi.ingsw.cg_2.model.GameMode;

/**
 * An abstract factory for creating Decks, it can be implemented to create decks
 * with a certain number of cards depending on the game mode.
 */
public abstract class DecksFactory {

    /**
     * Creates a new DecksFactory instantiating a different implementation
     * (transparent to the suer) based on the game mode.
     *
     * @param gameMode the game mode
     * @return the decks factory
     */
    public static DecksFactory create(GameMode gameMode) {
        if (gameMode == GameMode.ADVANCED) {
            // Instantiates a standard decks factory used for this game mode
            return new StandardDecksFactory();
        } else {
            throw new IllegalArgumentException("Invalid game mode.");
        }
    }

    /**
     * Creates a populated (@link Deck) of (@link ItemCard).
     *
     * @return a new (@link Deck) of (@link ItemCard).
     */
    public abstract Deck<ItemCard> createItemDeck();

    /**
     * Creates a populated (@link Deck) of (@link SectorCard).
     *
     * @return a new (@link Deck) of (@link SectorCard).
     */
    public abstract Deck<SectorCard> createSectorDeck();

    /**
     * Creates a populated (@link Deck) of (@link HatchCard).
     *
     * @return a new (@link Deck) of (@link HatchCard).
     */
    public abstract Deck<HatchCard> createHatchDeck();
}
