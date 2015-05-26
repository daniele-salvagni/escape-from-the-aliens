package it.polimi.ingsw.cg_2.model;

import it.polimi.ingsw.cg_2.model.deck.*;
import it.polimi.ingsw.cg_2.model.map.*;
import it.polimi.ingsw.cg_2.model.noise.Noise;
import it.polimi.ingsw.cg_2.model.player.*;

import it.polimi.ingsw.cg_2.model.player.Character;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.*;

public class GameTest {

    private static Game game;

    private static int numberOfPlayers;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void initClass() {

        numberOfPlayers = 7;

        ZoneFactory zFactory = ZoneFactory.newLoader(ZoneName.GALILEI);
        DecksFactory dFactory = new StandardDecksFactory();
        PlayersFactory pFactory = new StandardPlayersFactory();

        game = Game.initialize(zFactory, dFactory, pFactory, numberOfPlayers);

    }

    @Test
    public void shouldBeInitialized() {

        assertNotNull(game);

    }

    @Test
    public void shouldGetZone() {

        Zone zone = game.getZone();

        assertNotNull(zone);
        assertFalse(zone.getCoordinates().isEmpty());

    }

    @Test
    public void shouldGetItemDeck() {

        Deck<ItemCard> itemDeck = game.getItemDeck();

        assertNotNull(itemDeck);
        assertFalse(itemDeck.isEmpty());

    }

    @Test
    public void shouldGetSectorDeck() {

        Deck<SectorCard> sectorDeck = game.getSectorDeck();
        assertNotNull(sectorDeck);
        assertFalse(sectorDeck.isEmpty());

    }

    @Test
    public void shouldGetHatchDeck() {

        Deck<HatchCard> hatchDeck = game.getHatchDeck();
        assertNotNull(hatchDeck);
        assertFalse(hatchDeck.isEmpty());

    }

    @Test
    public void shouldGetPlayers() {

        List<Player> players = game.getPlayers();
        assertNotNull(players);
        assertEquals(numberOfPlayers, players.size());

    }

    @Test
    public void shouldGetNoises() {

        List<Noise> noises = game.getNoises();
        assertNotNull(noises);
        assertEquals(0, noises.size());

    }

    @Test
    public void shouldAddNoise() {

        /* CREATE A NEW GAME */
        int numberOfPlayers2 = 7;
        ZoneFactory zFactory2 = ZoneFactory.newLoader(ZoneName.GALILEI);
        DecksFactory dFactory2 = new StandardDecksFactory();
        PlayersFactory pFactory2 = new StandardPlayersFactory();
        Game game2 = Game.initialize(zFactory2, dFactory2, pFactory2,
                numberOfPlayers2);

        /* CREATE A NEW NOISE */
        // Create a Character
        CubicCoordinate charCoordinate = CubicCoordinate.create(0, 0, 0);
        Sector charPosition = new Sector(charCoordinate, Sector.SectorType
                .SAFE);
        Character character = new Character(charPosition, CharacterRank
                .CAPTAIN);
        // Instantiate a Player with the Character
        Player player = new Player(character);
        // Instantiate a Sector
        CubicCoordinate noiseCoordinate = CubicCoordinate.create(0, 0, 0);
        Sector sector = new Sector(noiseCoordinate, Sector.SectorType.SAFE);
        // Instantiate a Turn number
        int turnNumber = 7;
        // Instantiate a Noise
        Noise noise = new Noise(player, sector, turnNumber);

        /* TEST */
        game2.addNoise(noise);
        assertTrue(game2.getNoises().contains(noise));

    }

    @Test
    public void turnNumberShouldBeZero() {

        assertEquals(0, game.getTurnNumber());

    }

    @Test
    public void shouldSetTurnNumber() {

        /* CREATE A NEW GAME */
        int numberOfPlayers2 = 7;
        ZoneFactory zFactory2 = ZoneFactory.newLoader(ZoneName.GALILEI);
        DecksFactory dFactory2 = new StandardDecksFactory();
        PlayersFactory pFactory2 = new StandardPlayersFactory();
        Game game2 = Game.initialize(zFactory2, dFactory2, pFactory2,
                numberOfPlayers2);

        int expectedTurnNumber = 4;
        game2.setTurnNumber(expectedTurnNumber);

        assertEquals(expectedTurnNumber, game2.getTurnNumber());

    }

    @Test
    public void currentPlayerShouldBeNull() {

        assertNull(game.getCurrentPlayer());

    }

    @Test
    public void shouldSetCurrentPlayer() {

        /* CREATE A NEW GAME */
        int numberOfPlayers2 = 7;
        ZoneFactory zFactory2 = ZoneFactory.newLoader(ZoneName.GALILEI);
        DecksFactory dFactory2 = new StandardDecksFactory();
        PlayersFactory pFactory2 = new StandardPlayersFactory();
        Game game2 = Game.initialize(zFactory2, dFactory2, pFactory2,
                numberOfPlayers2);

        List<Player> players = game2.getPlayers();
        game2.setCurrentPlayer(players.get(0));

        assertEquals(players.get(0), game2.getCurrentPlayer());

    }

    @Test
    public void shouldGetUnmodifiableListOfPlayers() {

        List<Player> players = game.getPlayers();

        thrown.expect(UnsupportedOperationException.class);
        players.add(null);

    }

    @Test
    public void shouldGetUnmodifiableListOfNoises() {

        List<Noise> noises = game.getNoises();

        thrown.expect(UnsupportedOperationException.class);
        noises.add(null);

    }

}
