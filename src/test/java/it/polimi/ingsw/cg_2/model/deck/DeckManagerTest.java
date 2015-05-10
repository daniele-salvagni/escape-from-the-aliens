package it.polimi.ingsw.cg_2.model.deck;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_2.model.deck.HatchCard.HatchCardType;
import it.polimi.ingsw.cg_2.model.deck.ItemCard.ItemCardType;
import it.polimi.ingsw.cg_2.model.deck.SectorCard.SectorCardType;

import java.util.Collection;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

public class DeckManagerTest {

    private DeckManager<Card> deck;
    private Collection<Card> cards;

    @Before
    public void init() {
        /*
         * Creates a Set of cards, mixing item cards, sector cards and hatch
         * cards to populate the deck.
         */
        cards = new HashSet<Card>();
        cards.add(new ItemCard(ItemCardType.ADRENALINE));
        cards.add(new SectorCard(SectorCardType.NOISE));
        cards.add(new HatchCard(HatchCardType.RED));

        /* Instantiate a new deck populated with some test cards. */
        deck = new DeckManager<Card>(cards);
    }

    @Test
    public void shouldBeCreated() throws Exception {
        assertNotNull(deck);
    }

    @Test
    public void testDrawCard() throws Exception {
        throw new RuntimeException("not yet implemented");
    }

    @Test
    public void testDiscardCard() throws Exception {
        throw new RuntimeException("not yet implemented");
    }

    @Test
    public void testIsDeckEmpty() throws Exception {
        throw new RuntimeException("not yet implemented");
    }

    @Test
    public void testIsEmpty() throws Exception {
        throw new RuntimeException("not yet implemented");
    }

    @Test
    public void testShuffleDeck() throws Exception {
        throw new RuntimeException("not yet implemented");
    }

}
