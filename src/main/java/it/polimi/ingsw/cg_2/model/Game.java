package it.polimi.ingsw.cg_2.model;

import it.polimi.ingsw.cg_2.model.deck.*;
import it.polimi.ingsw.cg_2.model.map.Zone;
import it.polimi.ingsw.cg_2.model.map.ZoneFactory;
import it.polimi.ingsw.cg_2.model.noise.Noise;
import it.polimi.ingsw.cg_2.model.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the complete status of a Game, from here it is
 * possible
 * to access the whole model of the application.
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

    public Game(ZoneFactory zone, DecksFactory decks) {

        this.zone = zone.createZone();

        this.itemDeck = decks.createItemDeck();
        this.sectorDeck = decks.createSectorDeck();
        this.hatchDeck = decks.createHatchDeck();

        players = new ArrayList<>();
        noises = new ArrayList<>();

    }

    public static Game create(ZoneFactory zone, DecksFactory decks, int
            playersNumber) {

        return new Game(zone, decks);

    }

}
