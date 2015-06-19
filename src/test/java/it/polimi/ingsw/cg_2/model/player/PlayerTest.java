package it.polimi.ingsw.cg_2.model.player;

import it.polimi.ingsw.cg_2.model.deck.ItemCard;
import it.polimi.ingsw.cg_2.model.map.CubicCoordinate;
import it.polimi.ingsw.cg_2.model.map.Sector;
import it.polimi.ingsw.cg_2.model.map.Sector.SectorType;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.*;

public class PlayerTest {

    private SectorType sectorType;
    private CubicCoordinate coordinate;
    private Sector sector;
    private Character character;

    private Player player;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {

        sectorType = SectorType.SAFE;
        coordinate = CubicCoordinate.create(0, 0, 0);
        sector = new Sector(coordinate, sectorType);
        character = new Character(sector, CharacterRank.CAPTAIN);

        player = new Player(character);

    }

    @Test
    public void shouldBeCreated() {

        assertNotNull(player);

    }

    @Test
    public void shouldGetCharacter() {

        assertEquals(player.getCharacter(), character);

    }

    @Test
    public void shouldMoveCharacter() {

        Sector newPosition = new Sector(CubicCoordinate.create(3, -2, -1),
                SectorType.HUMAN);
        player.moveCharacter(newPosition);
        assertEquals(newPosition, player.getCharacter().getPosition());

    }

    @Test
    public void shouldHaveEmptyMovementHistoryAtTheBeginning() {

        assertTrue(player.getMovementHistory().isEmpty());

    }

    @Test
    public void shouldUpdateMovementHistory() {

        Sector newPosition = new Sector(CubicCoordinate.create(3, -2, -1),
                SectorType.HUMAN);
        player.moveCharacter(newPosition);

        List<Sector> movHistory = player.getMovementHistory();
        assertEquals(1, movHistory.size());
        assertTrue(movHistory.contains(newPosition));

    }

    @Test
    public void shouldSetConnected() {

        player.setConnected(true);
        assertTrue(player.isConnected());

    }

    @Test
    public void shouldSetDisconnected() {

        player.setConnected(false);
        assertFalse(player.isConnected());

    }

    @Test
    public void shouldSetSuspended() {

        player.setSuspended(true);
        assertTrue(player.isSuspended());

    }

    @Test
    public void shouldSetNotSuspended() {

        player.setSuspended(false);
        assertFalse(player.isSuspended());

    }

    @Test
    public void shouldHaveEmptyKillsList() {

        assertTrue(player.getKills().isEmpty());

    }

    @Test
    public void shouldSetKills() {

        SectorType sectorType2 = SectorType.DANGEROUS;
        CubicCoordinate coordinate2 = CubicCoordinate.create(2, -1, -1);
        Sector sector2 = new Sector(coordinate2, sectorType2);
        Character character2 = new Character(sector2, CharacterRank
                .PSYCHOLOGIST);

        player.addKill(character2);
        assertEquals(1, player.getKills().size());
        assertTrue(player.getKills().contains(character2));

    }

    @Test
    public void shouldHaveEmptyItemsList() {

        assertTrue(player.getHeldItems().isEmpty());

    }

    @Test
    public void shouldAddItem() {

        ItemCard card = new ItemCard(ItemCard.ItemCardType.ADRENALINE);
        player.giveItem(card);

        assertEquals(1, player.getHeldItems().size());
        assertTrue(player.getHeldItems().contains(card));

    }

    @Test
    public void shouldRemoveItem() {

        ItemCard card = new ItemCard(ItemCard.ItemCardType.ADRENALINE);

        player.giveItem(card);
        ItemCard removedCard = player.removeItem(ItemCard.ItemCardType
                .ADRENALINE);

        assertEquals(card, removedCard);
        assertTrue(player.getHeldItems().isEmpty());

        // Test return null if the player did not have the item
        assertNull(player.removeItem(ItemCard.ItemCardType.ADRENALINE));

    }

    @Test
    public void shouldRemoveSpecificItem() {

        ItemCard card = new ItemCard(ItemCard.ItemCardType.ADRENALINE);
        player.giveItem(card);

        assertTrue(player.removeItem(card));
        assertTrue(player.getHeldItems().isEmpty());

    }

    @Test
    public void shouldReturnTrueIfPlayerHaveAnItem() {

        ItemCard card = new ItemCard(ItemCard.ItemCardType.ADRENALINE);
        player.giveItem(card);
        assertTrue(player.haveItem(ItemCard.ItemCardType.ADRENALINE));

    }

    @Test
    public void shouldReturnFalseIfPlayerhaveNotAnItem() {

        assertFalse(player.haveItem(ItemCard.ItemCardType.ADRENALINE));

    }

    @Test
    public void shouldGetUnmodifiableMovementHistory() {

        List<Sector> movHistory = player.getMovementHistory();

        thrown.expect(UnsupportedOperationException.class);
        movHistory.add(null);

    }

    @Test
    public void shouldGetUnmodifiableHeldItems() {

        List<ItemCard> heldItems = player.getHeldItems();

        thrown.expect(UnsupportedOperationException.class);
        heldItems.add(null);

    }

    @Test
    public void shouldGetUnmodifiableKills() {

        List<Character> kills = player.getKills();

        thrown.expect(UnsupportedOperationException.class);
        kills.add(null);

    }

    @Test
    public void shouldGetUnmodifiableActiveItems() {

        List<ItemCard.ItemCardType> activeItems = player.getActiveItems();

        thrown.expect(UnsupportedOperationException.class);
        activeItems.add(null);

    }

    @Test
    public void shouldInitiallyGetEmptyActiveItemsList() {

        assertTrue(player.getActiveItems().isEmpty());

    }

    @Test
    public void shouldActivateItems() {

        ItemCard.ItemCardType expectedItem = ItemCard.ItemCardType.ADRENALINE;

        player.activateItem(expectedItem);

        assertTrue(player.getActiveItems().contains(expectedItem));

    }

    @Test
    public void shouldReturnTrueIfItemIsActive() {

        ItemCard.ItemCardType expectedItem = ItemCard.ItemCardType.ADRENALINE;
        player.activateItem(expectedItem);

        assertTrue(player.haveActiveItem(ItemCard.ItemCardType.ADRENALINE));

    }

    @Test
    public void shouldDeactiveteItem() {

        ItemCard.ItemCardType item = ItemCard.ItemCardType.ADRENALINE;
        player.activateItem(item);

        assertTrue(player.deactivateItem(ItemCard.ItemCardType.ADRENALINE));
        assertFalse(player.haveActiveItem(ItemCard.ItemCardType.ADRENALINE));

    }

    @Test
    public void shouldClearItems() {

        player.activateItem(ItemCard.ItemCardType.ADRENALINE);
        player.clearActiveItems();

        assertTrue(player.getActiveItems().isEmpty());

    }



}
