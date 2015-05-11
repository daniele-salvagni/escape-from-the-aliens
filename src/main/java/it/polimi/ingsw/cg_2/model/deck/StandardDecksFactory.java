package it.polimi.ingsw.cg_2.model.deck;

import static it.polimi.ingsw.cg_2.model.deck.HatchCard.HatchCardType.*;
import static it.polimi.ingsw.cg_2.model.deck.ItemCard.ItemCardType.*;
import static it.polimi.ingsw.cg_2.model.deck.SectorCard.SectorCardType.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a concrete factory that implements the abstract (@link
 * DecksFactory), it creates the following standard decks for the Advanced game
 * mode:
 * <ul>
 * <li><b>ItemDeck:</b> 2 Attack, 2 Teleport, 2 Adrenaline, 3 Sedatives, 2
 * Spotlight, 1 Defense.</li>
 * <li><b>SectorDeck:</b> 10 Noise (4 with object), 10 Deception (4 with
 * object), 5 Silence.</li>
 * <li><b>HatchDeck:</b> 3 Green, 3 Red.</li>
 * 
 * @see ItemCard
 * @see SectorCard
 * @see HatchCard
 */
public class StandardDecksFactory implements DecksFactory {

    public StandardDecksFactory() {
        super();
    }

    /*
     * We could use some loops to populate decks but it would only reduce
     * readability and editability.
     */

    @Override
    public Deck<ItemCard> createItemDeck() {

        List<ItemCard> cards = new ArrayList<ItemCard>();

        // 2 Attack cards
        cards.add(new ItemCard(ATTACK));
        cards.add(new ItemCard(ATTACK));

        // 2 Teleport cards
        cards.add(new ItemCard(TELEPORT));
        cards.add(new ItemCard(TELEPORT));

        // 2 Adrenaline cards
        cards.add(new ItemCard(ADRENALINE));
        cards.add(new ItemCard(ADRENALINE));

        // 3 Sedatives cards
        cards.add(new ItemCard(SEDATIVES));
        cards.add(new ItemCard(SEDATIVES));
        cards.add(new ItemCard(SEDATIVES));

        // 2 Spotlight cards
        cards.add(new ItemCard(SPOTLIGHT));
        cards.add(new ItemCard(SPOTLIGHT));

        // 1 Defense card
        cards.add(new ItemCard(DEFENSE));

        // Create a new Deck
        Deck<ItemCard> deckManager = new Deck<ItemCard>(cards);
        // And shuffle it
        deckManager.shuffleDeck();

        // Returns a new shuffled deck populated with the Item cards for the
        // Advanced Game mode (standard).
        return deckManager;
    }

    @Override
    public Deck<SectorCard> createSectorDeck() {

        List<SectorCard> cards = new ArrayList<SectorCard>();

        // 4 Noise cards with an object
        cards.add(new SectorCard(NOISE, true));
        cards.add(new SectorCard(NOISE, true));
        cards.add(new SectorCard(NOISE, true));
        cards.add(new SectorCard(NOISE, true));
        // 6 Noise cards without objects
        cards.add(new SectorCard(NOISE, false));
        cards.add(new SectorCard(NOISE, false));
        cards.add(new SectorCard(NOISE, false));
        cards.add(new SectorCard(NOISE, false));
        cards.add(new SectorCard(NOISE, false));
        cards.add(new SectorCard(NOISE, false));

        // 4 Deception cards with an object
        cards.add(new SectorCard(DECEPTION, true));
        cards.add(new SectorCard(DECEPTION, true));
        cards.add(new SectorCard(DECEPTION, true));
        cards.add(new SectorCard(DECEPTION, true));
        // 6 Deception cards without objects
        cards.add(new SectorCard(DECEPTION, false));
        cards.add(new SectorCard(DECEPTION, false));
        cards.add(new SectorCard(DECEPTION, false));
        cards.add(new SectorCard(DECEPTION, false));
        cards.add(new SectorCard(DECEPTION, false));
        cards.add(new SectorCard(DECEPTION, false));

        // 5 Silence cards without objects
        cards.add(new SectorCard(SILENCE));
        cards.add(new SectorCard(SILENCE));
        cards.add(new SectorCard(SILENCE));
        cards.add(new SectorCard(SILENCE));
        cards.add(new SectorCard(SILENCE));

        Deck<SectorCard> deckManager = new Deck<SectorCard>(cards);
        deckManager.shuffleDeck();

        // Returns a new shuffled deck populated with the Sector cards for the
        // Advanced Game mode (standard).
        return deckManager;
    }

    @Override
    public Deck<HatchCard> createHatchDeck() {

        List<HatchCard> cards = new ArrayList<HatchCard>();

        // 3 Green cards
        cards.add(new HatchCard(GREEN));
        cards.add(new HatchCard(GREEN));
        cards.add(new HatchCard(GREEN));

        // 3 Red cards
        cards.add(new HatchCard(RED));
        cards.add(new HatchCard(RED));
        cards.add(new HatchCard(RED));

        Deck<HatchCard> deckManager = new Deck<HatchCard>(cards);
        deckManager.shuffleDeck();

        // Returns a new shuffled deck populated with the Hatch cards for the
        // Advanced Game mode (standard).
        return deckManager;
    }

}
