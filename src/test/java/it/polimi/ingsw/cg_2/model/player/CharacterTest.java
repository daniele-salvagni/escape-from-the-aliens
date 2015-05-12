package it.polimi.ingsw.cg_2.model.player;

import it.polimi.ingsw.cg_2.model.map.CubicCoordinate;
import it.polimi.ingsw.cg_2.model.map.Sector;
import it.polimi.ingsw.cg_2.model.map.Sector.SectorType;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterTest {

    SectorType sectorType = SectorType.SAFE;
    CubicCoordinate coordinate = CubicCoordinate.create(0, 0, 0);
    Sector sector = new Sector(coordinate, sectorType);

    SectorType sectorType2 = SectorType.SAFE;
    CubicCoordinate coordinate2 = CubicCoordinate.create(1, 2, -3);
    Sector sector2 = new Sector(coordinate2, sectorType2);

    Character character;

    @Before
    public void init() {
        character = new Character(sector, CharacterRank.CAPTAIN);
    }

    @Test
    public void shouldBeCreatedTwoParameters() {
        assertNotNull(character);
    }

    @Test
    public void shouldBeCreatedThreeParameters() {
        assertNotNull(new Character(sector, CharacterRank.CAPTAIN,
                CharacterRace.ALIEN));
    }

    @Test
    public void shouldGetPosition() {
        assertEquals(character.getPosition(), sector);
    }

    @Test
    public void shouldSetPosition() {
        character.setPosition(sector2);
        assertEquals(character.getPosition(), sector2);
    }

    @Test
    public void shouldReturnTrueIfAlive() {
        assertTrue(character.isAlive());
    }

    @Test
    public void shouldReturnFalseIfNotAlive() {
        character.setAlive(false);
        assertFalse(character.isAlive());
    }

    @Test
    public void shouldGetType() {
        assertEquals(character.getRace(), CharacterRace.HUMAN);
    }

    @Test
    public void shouldSetType() {
        character.setRace(CharacterRace.ALIEN);
        assertEquals(character.getRace(), CharacterRace.ALIEN);
    }

    @Test
    public void shouldGetRank() {
        assertEquals(character.getRank(), CharacterRank.CAPTAIN);
    }

}
