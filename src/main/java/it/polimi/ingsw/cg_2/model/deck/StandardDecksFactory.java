package it.polimi.ingsw.cg_2.model.deck;

import it.polimi.ingsw.cg_2.model.deck.HatchCard.HatchCardType;
import it.polimi.ingsw.cg_2.model.deck.ItemCard.ItemCardType;
import it.polimi.ingsw.cg_2.model.deck.SectorCard.SectorCardType;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a concrete factory that implements the abstract (@link
 * DecksFactory), it creates the following decks:
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
public class StandardDecksFactory extends DecksFactory {

    @Override
    public Deck<ItemCard> createItemDeck() {

        /*
         * List of Item Cards: 2 Attack, 2 Teleport, 2 Adrenaline, 3 Sedatives,
         * 2 Spotlight, 1 Defense.
         */
        List<ItemCard> cards = new ArrayList<ItemCard>();
        cards.add(new ItemCard(ItemCardType.ATTACK));
        cards.add(new ItemCard(ItemCardType.ATTACK));
        cards.add(new ItemCard(ItemCardType.TELEPORT));
        cards.add(new ItemCard(ItemCardType.TELEPORT));
        cards.add(new ItemCard(ItemCardType.ADRENALINE));
        cards.add(new ItemCard(ItemCardType.ADRENALINE));
        cards.add(new ItemCard(ItemCardType.SEDATIVES));
        cards.add(new ItemCard(ItemCardType.SEDATIVES));
        cards.add(new ItemCard(ItemCardType.SEDATIVES));
        cards.add(new ItemCard(ItemCardType.SPOTLIGHT));
        cards.add(new ItemCard(ItemCardType.SPOTLIGHT));
        cards.add(new ItemCard(ItemCardType.DEFENSE));

        Deck<ItemCard> deckManager = new Deck<ItemCard>(cards);
        deckManager.shuffleDeck();

        // Returns a new shuffled deck populated with the Item cards for the
        // Advanced Game mode (standard).
        return deckManager;
    }

    @Override
    public Deck<SectorCard> createSectorDeck() {

        /*
         * List of Sector Cards: 10 Noise (4 with object), 10 Deception (4 with
         * object), 5 Silence.
         */
        List<SectorCard> cards = new ArrayList<SectorCard>();

        for (int i = 1; i <= 10; i++) {
            if (i <= 4) {
                // Add cards with Item (1-4)
                cards.add(new SectorCard(SectorCardType.NOISE, true));
                cards.add(new SectorCard(SectorCardType.DECEPTION, true));
            } else {
                // Add cards without Item (5-10)
                cards.add(new SectorCard(SectorCardType.NOISE));
                cards.add(new SectorCard(SectorCardType.DECEPTION));
            }
        }

        for (int i = 1; i <= 5; i++) {
            cards.add(new SectorCard(SectorCardType.SILENCE));
        }

        Deck<SectorCard> deckManager = new Deck<SectorCard>(cards);
        deckManager.shuffleDeck();

        // Returns a new shuffled deck populated with the Sector cards for the
        // Advanced Game mode (standard).
        return deckManager;
    }

    @Override
    public Deck<HatchCard> createHatchDeck() {

        /*
         * List of Hatch Cards: 3 Green, 3 Red.
         */
        List<HatchCard> cards = new ArrayList<HatchCard>();

        for (int i = 1; i < 3; i++) {
            cards.add(new HatchCard(HatchCardType.GREEN));
            cards.add(new HatchCard(HatchCardType.RED));
        }

        Deck<HatchCard> deckManager = new Deck<HatchCard>(cards);
        deckManager.shuffleDeck();

        // Returns a new shuffled deck populated with the Hatch cards for the
        // Advanced Game mode (standard).
        return deckManager;
    }

}
