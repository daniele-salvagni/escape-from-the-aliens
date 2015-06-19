package it.polimi.ingsw.cg_2.messages.responses;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * A response message for the AttackAction. Contains information about the
 * position and the made kills.
 */
public class AttackResponseMsg implements ResponseMsg {

    private final String coordinate;
    private final Map<Integer, String> kills;
    private final List<Integer> survivors;

    /**
     * Create a new AttackResponseMsg. Coordinates are encoded in a string in
     * the COL:ROW format, players by a number based on their turn order (from
     * 0).
     *
     * @param coordinate the coordinate of the attack
     * @param kills      the map of killed players and their race (can be empty)
     * @param survivors  the list of survived player (can be empty)
     */
    public AttackResponseMsg(String coordinate, Map<Integer, String> kills,
            List<Integer> survivors) {

        if (coordinate == null) {
            throw new IllegalArgumentException("coordinate cannot be null.");
        } else if (kills == null) {
            throw new IllegalArgumentException("kills cannot be null.");
        } else if (survivors == null) {
            throw new IllegalArgumentException("survivors cannot be null.");
        }

        this.coordinate = coordinate;
        this.kills = kills;
        this.survivors = survivors;

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

        return Collections.unmodifiableMap(kills);

    }

    /**
     * Get the list of survived players that used a defense card (could be
     * empty).
     *
     * @return the list of survived players (an unmodifiable view)
     */
    public List<Integer> getSurvivors() {

        return Collections.unmodifiableList(survivors);

    }

}
