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

        Sector newPosition = new Sector(CubicCoordinate.create(3, -2, -1), SectorType.HUMAN);
        player.moveCharacter(newPosition);
        assertEquals(newPosition, player.getCharacter().getPosition());

    }

    @Test
    public void shouldHaveEmptyMovementHistoryAtTheBeginning() {

        assertTrue(player.getMovementHistory().isEmpty());

    }

    @Test
    public void shouldUpdateMovementHistory() {

        Sector newPosition = new Sector(CubicCoordinate.create(3, -2, -1), SectorType.HUMAN);
        player.moveCharacter(newPosition);

        List<Sector> movHistory = player.getMovementHistory();
        assertEquals(1, movHistory.size());
        assertTrue(movHistory.contains(newPosition));

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

}
