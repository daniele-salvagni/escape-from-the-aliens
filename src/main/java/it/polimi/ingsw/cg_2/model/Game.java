package it.polimi.ingsw.cg_2.model;

import it.polimi.ingsw.cg_2.model.deck.*;
import it.polimi.ingsw.cg_2.model.map.Sector;
import it.polimi.ingsw.cg_2.model.map.Zone;
import it.polimi.ingsw.cg_2.model.map.ZoneFactory;
import it.polimi.ingsw.cg_2.model.noise.Noise;
import it.polimi.ingsw.cg_2.model.player.Player;
import it.polimi.ingsw.cg_2.model.player.PlayersFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents the complete status of a Game, from here it is
 * possible to access the whole model of the application.
 */
public class Game {

    private final Zone zone;

    private final Deck<ItemCard> itemDeck;
    private final Deck<SectorCard> sectorDeck;
    private final Deck<HatchCard> hatchDeck;

    private final List<Player> players;
    private final List<Noise> noises;

    private int turnNumber;
    private Player currentPlayer;

    /**
     * The private constructor of the game, it does instantiate instantiates
     * all
     * the class attributes.
     *
     * @param zFactory an object implementation of a {@link ZoneFactory} used
     *                 to
     *                 create the map of the Game
     * @param dFactory an object implementation of a {@link DecksFactory} used
     *                 to populate all the decks of the Game
     * @param pFactory an object implementation of a {@link PlayersFactory}
     *                 used
     *                 to populate the list of players of the Game
     * @param pNumber  the number of players in this Game
     */
    private Game(ZoneFactory zFactory, DecksFactory dFactory, PlayersFactory
            pFactory, int pNumber) {

        /* The zone factory, the parameters to create the zone (type, etc)
        are already set, so we just instantiate a new zone. (details depends
        on the implementation) */
        this.zone = zFactory.createZone();

        /* The decks factory is able to correctly populate all the decks
        needed in the game. (details depends on the implementation) */
        this.itemDeck = dFactory.createItemDeck();
        this.sectorDeck = dFactory.createSectorDeck();
        this.hatchDeck = dFactory.createHatchDeck();

        /* Populates a list of players with the characters in their
        appropriate location. (details depends on the implementation) */
        this.players = pFactory.createPlayers(pNumber, zone.getHumanSector(),
                zone.getAlienSector());

        noises = new ArrayList<>();
        turnNumber = 0;

    }

    /**
     * Initializes a new Game, every part is populated with the provided
     * factories, the turn number to zero and the list of noises empty.
     *
     * @param zFactory an object implementation of a {@link ZoneFactory} used
     *                 to
     *                 create the map of the Game
     * @param dFactory an object implementation of a {@link DecksFactory} used
     *                 to populate all the decks of the Game
     * @param pFactory an object implementation of a {@link PlayersFactory}
     *                 used
     *                 to populate the list of players of the Game
     * @param pNumber  the number of players in this Game
     * @return a new initialized Game
     */
    public static Game initialize(ZoneFactory zFactory, DecksFactory
            dFactory, PlayersFactory pFactory, int pNumber) {

        return new Game(zFactory, dFactory, pFactory, pNumber);

    }

    /**
     * Gets the {@link Zone} of the Game.
     *
     * @return the {@link Zone} of the Game
     */
    public Zone getZone() {

        return zone;

    }

    /**
     * Gets the {@link Deck} of {@link ItemCard}.
     *
     * @return the {@link Deck} of {@link ItemCard}
     */
    public Deck<ItemCard> getItemDeck() {

        return itemDeck;

    }

    /**
     * Gets the {@link Deck} of {@link SectorCard}.
     *
     * @return the {@link Deck} of {@link SectorCard}
     */
    public Deck<SectorCard> getSectorDeck() {

        return sectorDeck;

    }

    /**
     * Gets the {@link Deck} of {@link HatchCard}.
     *
     * @return the {@link Deck} of {@link HatchCard}
     */
    public Deck<HatchCard> getHatchDeck() {

        return hatchDeck;

    }

    /**
     * Gets the list of {@link Player}s of the Game. A {@link
     * Collections#unmodifiableList(List)} is returned to reduce mutability.
     *
     * @return the list of {@link Player}s of the Game
     */
    public List<Player> getPlayers() {

        return Collections.unmodifiableList(players);

    }

    /**
     * Gets the list of {@link Noise}s of the Game. A {@link
     * Collections#unmodifiableList(List)} is returned to reduce mutability.
     *
     * @return the list of {@link Noise}s of the Game
     */
    public List<Noise> getNoises() {

        return Collections.unmodifiableList(noises);

    }

    /**
     * Add a new {@link Noise} to the list of Noises
     *
     * @param noise the noise to add
     */
    public void addNoise(Noise noise) {

        noises.add(noise);

    }

    /**
     * Gets the current turn number of the Game
     *
     * @return the current turn number
     */
    public int getTurnNumber() {

        return turnNumber;

    }

    /**
     * Gets the current {@link Player} of the Game
     *
     * @return the current {@link Player} of the Game
     */
    public Player getCurrentPlayer() {

        return currentPlayer;

    }

    /**
     * Sets the turn number of the Game
     *
     * @param turnNumber the turn number to set
     */
    public void setTurnNumber(int turnNumber) {

        this.turnNumber = turnNumber;

    }

    /**
     * Sets the current {@link Player} of the game
     *
     * @param currentPlayer the current player
     */
    public void setCurrentPlayer(Player currentPlayer) {

        this.currentPlayer = currentPlayer;

    }

    /**
     * Search for players in a specific coordinate.
     *
     * @param sector the sector where to search players
     * @return a list of the players in that sector (could be empty)
     */
    public List<Player> getPlayersInSector(Sector sector) {

        List<Player> playersFound = new ArrayList<>();

        for (Player player : players) {

            if (player.getCharacter().getPosition().getCooridnate().equals
                    (sector.getCooridnate())) {
                playersFound.add(player);
            }

        }

        return playersFound;

    }

    /**
     * Gets the number of a certain player (based on the players order).
     *
     * @param player the player
     * @return the number of the player
     */
    public int getPlayerNumber(Player player) {

        return players.indexOf(player);

    }

    /**
     * Get the number of the current player playing the turn.
     *
     * @return the number of the current player
     */
    public int getCurrentPlayerNumber() {

        return getPlayerNumber(getCurrentPlayer());

    }

}

