package it.polimi.ingsw.cg_2.model.noise;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_2.model.map.CubicCoordinate;
import it.polimi.ingsw.cg_2.model.map.Sector;
import it.polimi.ingsw.cg_2.model.map.Sector.SectorType;
import it.polimi.ingsw.cg_2.model.player.CharacterRank;
import it.polimi.ingsw.cg_2.model.player.Player;
import it.polimi.ingsw.cg_2.model.player.Character;

import org.junit.BeforeClass;
import org.junit.Test;

public class NoiseTest {

    private static Player player;
    private static Sector sector;
    private static int turnNumber;
    private static Noise noise;

    @BeforeClass
    public static void initClass() {

        // Create a Character
        CubicCoordinate charCoordinate = CubicCoordinate.create(0, 0, 0);
        Sector charPosition = new Sector(charCoordinate, SectorType.SAFE);
        Character character = new Character(charPosition, CharacterRank.CAPTAIN);

        // Instantiate a Player with the Character
        player = new Player(character);

        // Instantiate a Sector
        CubicCoordinate noiseCoordinate = CubicCoordinate.create(0, 0, 0);
        sector = new Sector(noiseCoordinate, SectorType.SAFE);

        // Instantiate a Turn number
        turnNumber = 7;

        // Instantiate a Noise
        noise = new Noise(player, sector, turnNumber);

    }

    @Test
    public void shouldBeCreated() {

        assertNotNull(noise);

    }

    @Test
    public void shouldGetPlayer() {

        assertEquals(noise.getPlayer(), player);

    }

    @Test
    public void shouldGetSector() {

        assertEquals(noise.getSector(), sector);

    }

    @Test
    public void shouldGetTurnNumber() {

        assertEquals(noise.getTurnNumber(), turnNumber);

    }

}
