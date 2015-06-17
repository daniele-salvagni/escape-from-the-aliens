package it.polimi.ingsw.cg_2.messages.responses;

/**
 * A response message for the AttackAction. Contains information about the
 * position and the made kills.
 */
public class AttackResponseMsg {

    private final String coordinate;
    private final int[] kills;

    public AttackResponseMsg(String coordinate, int[] kills) {

        this.coordinate = coordinate;
        this.kills = kills;

    }

    public String getCoordinate() {

        return coordinate;
    }

    public int[] getKills() {

        return kills;
    }

}
