package it.polimi.ingsw.cg_2.model.noise;

import it.polimi.ingsw.cg_2.model.map.Sector;
import it.polimi.ingsw.cg_2.model.player.Player;

/**
 * This class represents a Noise made by a player, the type of noise is not
 * specified so it is safe to broadcast this information to all the players.
 */
public class Noise {

    private final Player player;
    private final Sector sector;
    private final int turnNumber;

    /**
     * Instantiates a new noise.
     *
     * @param player the player
     * @param sector the sector
     * @param turnNumber the turn number
     */
    public Noise(Player player, Sector sector, int turnNumber) {

        this.player = player;
        this.sector = sector;
        this.turnNumber = turnNumber;

    }

    /**
     * Gets the player that made the noise.
     *
     * @return the player that made the noise
     */
    public Player getPlayer() {

        return player;

    }

    /**
     * Gets the sector in which the noise has been made.
     *
     * @return the sector of the noise
     */
    public Sector getSector() {

        return sector;

    }

    /**
     * Gets the turn number when the noise occurred.
     *
     * @return the turn number when the noise occurred
     */
    public int getTurnNumber() {

        return turnNumber;

    }

}
