package it.polimi.ingsw.cg_2.model.noise;

import it.polimi.ingsw.cg_2.model.map.Sector;
import it.polimi.ingsw.cg_2.model.player.Player;

public class Noise {

    private final Player player;
    private final Sector sector;
    private final int turnNumber;

    public Noise(Player player, Sector sector, int turnNumber) {

        this.player = player;
        this.sector = sector;
        this.turnNumber = turnNumber;

    }

    public Player getPlayer() {

        return player;

    }

    public Sector getSector() {

        return sector;

    }

    public int getTurnNumber() {

        return turnNumber;

    }

}
