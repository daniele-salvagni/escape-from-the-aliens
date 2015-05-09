package it.polimi.ingsw.cg_2.model.deck;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_2.model.deck.ItemCard.ItemCardType;

import org.junit.Test;

public class ItemCardTest {

    @Test
    public void testConstructor() throws Exception {
        ItemCardType cardType = ItemCardType.ADRENALINE;
        ItemCard itemCard = new ItemCard(cardType);
        assertNotNull(itemCard);
    }

    @Test
    public void testGetType() throws Exception {
        ItemCardType expectedType = ItemCardType.ADRENALINE;
        ItemCard itemCard = new ItemCard(expectedType);
        assertEquals(itemCard.getType(), expectedType);
    }

}
