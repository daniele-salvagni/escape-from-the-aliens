package it.polimi.ingsw.cg_2.model.deck;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import it.polimi.ingsw.cg_2.model.deck.ItemCard.ItemCardType;

import org.junit.Test;

public class ItemCardTest {

    ItemCardType type = ItemCardType.ADRENALINE;

    @Test
    public void souldBeCreated() {
        ItemCard card = new ItemCard(type);
        assertNotNull(card);
    }

    @Test
    public void shouldGetType() {
        for (ItemCardType type : ItemCardType.values()) {
            assertEquals(type, getType(type));
        }

    }

    ItemCardType getType(ItemCardType type) {
        ItemCard card = new ItemCard(type);
        return card.getType();
    }
}
