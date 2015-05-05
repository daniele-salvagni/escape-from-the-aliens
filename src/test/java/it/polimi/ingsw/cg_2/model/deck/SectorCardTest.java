package it.polimi.ingsw.cg_2.model.deck;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_2.model.deck.SectorCard.SectorCardType;

import org.junit.Test;

public class SectorCardTest {

    SectorCardType type = SectorCardType.NOISE;

    @Test
    public void souldBeCreated() {
        SectorCard card = new SectorCard(type);
        assertNotNull(card);
    }

    @Test
    public void souldBeCreatedWithTwoParameters() {
        SectorCard card = new SectorCard(type, false);
        assertNotNull(card);
    }

    @Test
    public void shouldNotContainItem() {
        SectorCard card = new SectorCard(type);
        assertEquals(false, card.containsItem());

        card = new SectorCard(type, false);
        assertEquals(false, card.containsItem());
    }

    @Test
    public void shouldContainItem() {
        SectorCard card = new SectorCard(type, true);
        assertEquals(true, card.containsItem());
    }

}
