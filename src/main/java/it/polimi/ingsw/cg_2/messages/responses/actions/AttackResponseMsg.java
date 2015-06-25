package it.polimi.ingsw.cg_2.messages.responses.actions;

import java.util.*;

/**
 * A response message for the AttackAction. Contains information about the
 * position and the made kills.
 */
public class AttackResponseMsg extends ActionResponseMsg {

    private final String coordinate;
    private final HashMap<Integer, String> kills;
    private final ArrayList<Integer> survivors;

    /**
     * Create a new AttackResponseMsg. Coordinates are encoded in a string in
     * the COL:ROW format, players by a number based on their turn order (from
     * 0).
     *
     * @param coordinate the coordinate of the attack
     * @param kills      the map of killed players and their race (can be
     *                   empty)
     * @param survivors  the list of survived player (can be empty)
     */
    public AttackResponseMsg(boolean success, String coordinate, Map<Integer,
            String> kills, List<Integer> survivors) {

        super(success);

        if (coordinate == null) {
            throw new IllegalArgumentException("coordinate cannot be null.");
        } else if (kills == null) {
            throw new IllegalArgumentException("kills cannot be null.");
        } else if (survivors == null) {
            throw new IllegalArgumentException("survivors cannot be null.");
        }

        this.coordinate = coordinate;
        this.kills = new HashMap<>(kills);
        this.survivors = new ArrayList<>(survivors);

    }

    /**
     * Get the coordinate of the attack.
     *
     * @return the coordinate of the attack
     */
    public String getCoordinate() {

        return coordinate;

    }

    /**
     * Get the map of killed players and their race (could be empty).
     *
     * @return the map of killed players (an unmodifiable view)
     */
    public Map<Integer, String> getKills() {

        return kills;

    }

    /**
     * Get the list of survived players that used a defense card (could be
     * empty).
     *
     * @return the list of survived players (an unmodifiable view)
     */
    public List<Integer> getSurvivors() {

        return survivors;

    }

}
