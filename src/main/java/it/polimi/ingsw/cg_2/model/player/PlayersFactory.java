package it.polimi.ingsw.cg_2.model.player;

import it.polimi.ingsw.cg_2.model.map.Sector;

import java.util.List;

/**
 * This abstract factory creates a List of players with characters of a certain
 * rank based on the implementation and depending on the total number of
 * players.
 */
public interface PlayersFactory {

    /**
     * Creates a new List of Players, each containing a character (random,
     * based on the implementation) positioned in the appropriate human or
     * alien sector.
     *
     * @param number      the number of players to create
     * @param humanSector the human sector of the {@link it.polimi.ingsw.cg_2
     *                    .model.map.Zone}
     * @param alienSector the alien sector of the {@link it.polimi.ingsw.cg_2
     *                    .model.map.Zone}
     * @return a new List of players
     */
    List<Player> createPlayers(int number, Sector humanSector, Sector
            alienSector);

}
