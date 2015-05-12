package it.polimi.ingsw.cg_2.model.player;

import it.polimi.ingsw.cg_2.model.map.CubicCoordinate;
import it.polimi.ingsw.cg_2.model.map.Sector;
import it.polimi.ingsw.cg_2.model.map.Sector.SectorType;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    private SectorType sectorType;
    private CubicCoordinate coordinate;
    private Sector sector;
    private Character character;

    private Player player;

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

        // TODO

    }

    @Test
    public void shouldUpdateMovementHistory() {

        // TODO

    }

}
