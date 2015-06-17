package it.polimi.ingsw.cg_2.messages.responses;

/**
 * A response message for the AttackAction. Contains information about the
 * position and the made kills.
 */
public class AttackResponseMsg implements ResponseMsg {

    private final String coordinate;
    private final int[] kills;

    /**
     * Create a new AttackResponseMsg. Coordinates are encoded in a string in
     * the COL:ROW format, players by a number based on their turn order (from
     * 0).
     *
     * @param coordinate the coordinate of the attack
     * @param kills      the list of killed players
     */
    public AttackResponseMsg(String coordinate, int[] kills) {

        this.coordinate = coordinate;
        this.kills = kills;

    }

    /**
     * Get the coordinate of the attack
     *
     * @return the coordinate of the attack
     */
    public String getCoordinate() {

        return coordinate;
    }

    /**
     * Get the list of killed players (could be empty)
     *
     * @return the list of killed players
     */
    public int[] getKills() {

        return kills;
    }

}
