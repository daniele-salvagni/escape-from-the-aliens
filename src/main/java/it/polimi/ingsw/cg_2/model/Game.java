package it.polimi.ingsw.cg_2.model;

import it.polimi.ingsw.cg_2.model.deck.*;
import it.polimi.ingsw.cg_2.model.map.Zone;
import it.polimi.ingsw.cg_2.model.map.ZoneFactory;
import it.polimi.ingsw.cg_2.model.noise.Noise;
import it.polimi.ingsw.cg_2.model.player.Player;
import it.polimi.ingsw.cg_2.model.player.PlayersFactory;
import it.polimi.ingsw.cg_2.model.player.StandardPlayersFactory;

import java.util.ArrayList;
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
     * The private constructor of the game, it does instantiate instantiates all
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


}
