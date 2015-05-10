package it.polimi.ingsw.cg_2.model.deck;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_2.model.deck.HatchCard.HatchCardType;

import org.junit.Test;

public class HatchCardTest {

    HatchCardType type = HatchCardType.RED;

    @Test
    public void souldBeCreated() {
        HatchCard card = new HatchCard(type);
        assertNotNull(card);
    }

    @Test
    public void shouldGetType() {
        for (HatchCardType type : HatchCardType.values()) {
            assertEquals(type, getType(type));
        }

    }

    HatchCardType getType(HatchCardType type) {
        HatchCard card = new HatchCard(type);
        return card.getType();
    }

}
