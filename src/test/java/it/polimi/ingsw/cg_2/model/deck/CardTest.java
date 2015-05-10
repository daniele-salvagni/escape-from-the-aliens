package it.polimi.ingsw.cg_2.model.deck;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import it.polimi.ingsw.cg_2.model.deck.ItemCard.ItemCardType;
import it.polimi.ingsw.cg_2.model.deck.SectorCard.SectorCardType;
import it.polimi.ingsw.cg_2.model.deck.HatchCard.HatchCardType;

@RunWith(Parameterized.class)
public class CardTest {

    public Card card;

    private static ItemCardType itemCardType = ItemCardType.ADRENALINE;
    private static SectorCardType sectorCardType = SectorCardType.NOISE;
    private static HatchCardType hatchCardType = HatchCardType.RED;

    public CardTest(Card card) {
        this.card = card;
    }

    @Test
    public final void shouldBeCreated() {
        assertNotNull(card);
    }

    @Test
    public final void shouldGetType() {
        if (card instanceof ItemCard) {
            assertEquals(itemCardType, card.getType());
        } else if (card instanceof SectorCard) {
            assertEquals(sectorCardType, card.getType());
        } else if (card instanceof HatchCard) {
            assertEquals(hatchCardType, card.getType());
        }
    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        return Arrays.asList(new Object[] { new ItemCard(itemCardType) },
                new Object[] { new SectorCard(sectorCardType) },
                new Object[] { new HatchCard(hatchCardType) });
    }

}
