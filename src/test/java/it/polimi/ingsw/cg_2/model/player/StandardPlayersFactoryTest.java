package it.polimi.ingsw.cg_2.model.player;

import it.polimi.ingsw.cg_2.model.map.CubicCoordinate;
import it.polimi.ingsw.cg_2.model.map.Sector;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.*;

public class StandardPlayersFactoryTest {

    private static StandardPlayersFactory factory;

    private static Sector humanSector;
    private static Sector alienSector;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void initClass() {

        factory = new StandardPlayersFactory();

        humanSector = new Sector(CubicCoordinate.create(1, 2, -3), Sector
                .SectorType.HUMAN);
        alienSector = new Sector(CubicCoordinate.create(2, 3, -5), Sector
                .SectorType.ALIEN);

    }

    @Test
    public void shouldBeCreated() {

        assertNotNull(factory);

    }

    @Test
    public void shouldCreateHalfHumansHalfAliens() {

        int expectedPlayers = 6;
        int expectedHumans = 3;
        int expectedAliens = 3;

        testCharacterRaces(expectedPlayers, expectedHumans, expectedAliens);

    }

    @Test
    public void shouldCreateMoreAliensIfOdd() {

        int expectedPlayers = 7;
        int expectedHumans = 3;
        int expectedAliens = 4;

        testCharacterRaces(expectedPlayers, expectedHumans, expectedAliens);

    }

    @Test
    public void testUpperLimit() {

        int expectedPlayers = 8;
        int expectedHumans = 4;
        int expectedAliens = 4;

        testCharacterRaces(expectedPlayers, expectedHumans, expectedAliens);

    }

    @Test
    public void testLowerLimit() {

        int expectedPlayers = 2;
        int expectedHumans = 1;
        int expectedAliens = 1;

        testCharacterRaces(expectedPlayers, expectedHumans, expectedAliens);
    }

    @Test
    public void shouldFailCreatingMoreThan8Players() {

        thrown.expect(IllegalArgumentException.class);

        int expectedPlayers = 9;
        int expectedHumans = 4;
        int expectedAliens = 5;

        testCharacterRaces(expectedPlayers, expectedHumans, expectedAliens);

    }

    @Test
    public void shouldFailCreatingLessThan2Players() {

        thrown.expect(IllegalArgumentException.class);

        int expectedPlayers = 1;
        int expectedHumans = 0;
        int expectedAliens = 1;

        testCharacterRaces(expectedPlayers, expectedHumans, expectedAliens);

    }

    private void testCharacterRaces(int expectedPlayers, int
            expectedHumans, int expectedAliens) {

        List<Player> players = factory.createPlayers(expectedPlayers,
                humanSector,
                alienSector);

        int humanCount = 0;
        int alienCount = 0;

        for (Player player : players) {
            if (player.getCharacter().getRace() == CharacterRace.HUMAN) {
                humanCount++;
            } else if (player.getCharacter().getRace() == CharacterRace.ALIEN) {
                alienCount++;
            }
        }

        assertEquals(expectedPlayers, players.size());
        assertEquals(expectedHumans, humanCount);
        assertEquals(expectedAliens, alienCount);

    }


}
