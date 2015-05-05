package it.polimi.ingsw.cg_2.model.deck;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_2.model.deck.HatchCard.HatchCardType;

import org.junit.Test;

public class HatchCardTest {

    @Test
    public void souldBeCreated() {
        HatchCardType type = HatchCardType.RED;
        HatchCard card = new HatchCard(type);
        assertNotNull(card);
    }

    @Test
    public void shouldGetType() {
        HatchCardType expectedType = HatchCardType.RED;
        HatchCard card = new HatchCard(expectedType);
        assertEquals(expectedType, card.getType());
    }

}
