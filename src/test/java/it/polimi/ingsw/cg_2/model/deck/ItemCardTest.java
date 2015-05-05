package it.polimi.ingsw.cg_2.model.deck;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import it.polimi.ingsw.cg_2.model.deck.ItemCard.ItemCardType;

import org.junit.Test;

public class ItemCardTest {

    @Test
    public void souldBeCreated() {
        ItemCardType type = ItemCardType.ADRENALINE;
        ItemCard card = new ItemCard(type);
        assertNotNull(card);
    }

    @Test
    public void shouldGetType() {
        ItemCardType expectedType = ItemCardType.ADRENALINE;
        ItemCard card = new ItemCard(expectedType);
        assertEquals(expectedType, card.getType());
    }
}
