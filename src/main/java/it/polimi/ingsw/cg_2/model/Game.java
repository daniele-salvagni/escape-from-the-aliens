package it.polimi.ingsw.cg_2.model;

import it.polimi.ingsw.cg_2.model.deck.Deck;
import it.polimi.ingsw.cg_2.model.deck.HatchCard;
import it.polimi.ingsw.cg_2.model.deck.ItemCard;
import it.polimi.ingsw.cg_2.model.deck.SectorCard;
import it.polimi.ingsw.cg_2.model.map.Zone;
import it.polimi.ingsw.cg_2.model.noise.Noise;
import it.polimi.ingsw.cg_2.model.player.Player;

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

    public Game() {

        zone = null;
        itemDeck = null;
        sectorDeck = null;
        hatchDeck = null;
        players = null;
        noises = null;

    }


}
