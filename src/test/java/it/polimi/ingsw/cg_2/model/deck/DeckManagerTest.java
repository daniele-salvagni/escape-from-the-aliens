package it.polimi.ingsw.cg_2.model.deck;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_2.model.deck.HatchCard.HatchCardType;
import it.polimi.ingsw.cg_2.model.deck.ItemCard.ItemCardType;
import it.polimi.ingsw.cg_2.model.deck.SectorCard.SectorCardType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DeckManagerTest {

    private DeckManager<Card> deck;
    private List<Card> cards;

    @Before
    public void init() {

        /*
         * Creates a Set of cards, mixing item cards, sector cards and hatch
         * cards to populate the deck.
         */
        cards = new ArrayList<Card>();
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
    public void shouldDrawCards() throws Exception {
        
        // We expect cards to be drawn in reverse order (works like a stack)
        Collections.reverse(cards);
        for (Card card : cards) {
            assertEquals(card, deck.drawCard());
        }
        
    }

    @Test
    public void shouldDrawNullIfEmpty() throws Exception {
        
        // We empty the deck
        for (int i = 0; i < cards.size(); i++) {
            deck.drawCard();
        }
        assertNull(deck.drawCard());
        
    }

    @Test
    public void shouldUseDiscardPileIfDeckIsEmpty() throws Exception {
        
        // We empty the deck except for one card
        for (int i = 0; i < (cards.size() - 1); i++) {
            deck.drawCard();
        }
        // We empty the deck but we save a reference to the last card
        Card expectedCard = deck.drawCard();
        // We put it back in the discard pile of the deck
        deck.discardCard(expectedCard);
        // We check if we get the same card
        assertEquals(expectedCard, deck.drawCard());
        
    }

    @Test
    public void shouldReturnTrueIfEmpry() throws Exception {
        throw new RuntimeException("not yet implemented");
    }

    @Test
    public void shouldReturnTrueIfDiscardIsEmpty() throws Exception {
        throw new RuntimeException("not yet implemented");
    }

    @Test
    public void testShuffleDeck() throws Exception {
        throw new RuntimeException("not yet implemented");
    }

}
