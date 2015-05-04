package it.polimi.ingsw.cg_2.model.deck;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_2.model.deck.SectorCard.SectorCardType;

import org.junit.Test;

public class SectorCardTest {

    @Test
    public void testConstructor() throws Exception {
        SectorCardType cardType = SectorCardType.NOISE;
        SectorCard sectorCard = new SectorCard(cardType);
        assertNotNull(sectorCard);
    }

    /*@Test
    public void testConstructorWithObject() throws Exception {
        throw new RuntimeException("not yet implemented");
    }

    @Test
    public void testContainsItem() throws Exception {
        throw new RuntimeException("not yet implemented");
    }

    @Test
    public void testGetType() throws Exception {
        throw new RuntimeException("not yet implemented");
    }*/

}
