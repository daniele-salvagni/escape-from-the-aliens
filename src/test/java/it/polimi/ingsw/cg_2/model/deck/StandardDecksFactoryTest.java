package it.polimi.ingsw.cg_2.model.deck;

import static org.junit.Assert.*;
import static it.polimi.ingsw.cg_2.model.deck.HatchCard.HatchCardType.*;
import static it.polimi.ingsw.cg_2.model.deck.ItemCard.ItemCardType.*;
import static it.polimi.ingsw.cg_2.model.deck.SectorCard.SectorCardType.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class StandardDecksFactoryTest {

    private static StandardDecksFactory factory;

    @BeforeClass
    public static void init() {
        factory = new StandardDecksFactory();
    }

    @Test
    public void shouldBeCreated() {
        assertNotNull(factory);
    }

    @Test
    public void shouldPopulateItemDeckCorrectly() {
        Deck<ItemCard> deck = factory.createItemDeck();
        ItemCard card;

        // It should create 2 Attack, 2 Teleport, 2 Adrenaline, 3 Sedatives, 2
        // Spotlight, 1 Defense
        int[] expectedNumberOfCards = new int[] { 2, 2, 2, 3, 2, 1 };
        int[] numberOfCards = new int[] { 0, 0, 0, 0, 0, 0 };

        while (!deck.isEmpty()) {
            card = deck.drawCard();
            if (card.getType() == ATTACK) {
                ++numberOfCards[0];
            } else if (card.getType() == TELEPORT) {
                ++numberOfCards[1];
            } else if (card.getType() == ADRENALINE) {
                ++numberOfCards[2];
            } else if (card.getType() == SEDATIVES) {
                ++numberOfCards[3];
            } else if (card.getType() == SPOTLIGHT) {
                ++numberOfCards[4];
            } else if (card.getType() == DEFENSE) {
                ++numberOfCards[5];
            } else {
                throw new AssertionError("Invalid card has been found.");
            }
        }

        assertArrayEquals(expectedNumberOfCards, numberOfCards);

    }
}
