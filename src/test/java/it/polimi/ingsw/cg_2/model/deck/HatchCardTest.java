package it.polimi.ingsw.cg_2.model.deck;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_2.model.deck.HatchCard.HatchCardType;

import org.junit.Test;

public class HatchCardTest {

    @Test
    public void testConstructor() throws Exception {
        HatchCardType cardType = HatchCardType.RED;
        HatchCard hatchCard = new HatchCard(cardType);
        assertNotNull(hatchCard);
    }

    @Test
    public void testGetType() throws Exception {
        HatchCardType expectedType = HatchCardType.RED;
        HatchCard hatchCard = new HatchCard(expectedType);
        assertEquals(hatchCard.getType(), expectedType);
    }

}
