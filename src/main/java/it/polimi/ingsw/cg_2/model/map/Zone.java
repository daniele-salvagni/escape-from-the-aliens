package it.polimi.ingsw.cg_2.model.map;

import java.util.Map;
import java.util.Set;

/**
 * This class represents a Zone (the map of this game), a Zone should contain:
 * <ul>
 * <li>Exactly one HUMAN Sector.</li>
 * <li>Exactly one ALIEN Sector.</li>
 * <li>At least one HATCH sector.</li>
 * </ul>
 */
public interface Zone {

    /**
     * Gets all the sectors of the StandardZone mapped by CubicCoordinate. We
     * return a copy of the Map to minimize mutability.
     *
     * @return a new Map containing all the sectors of the zone
     */
    public Map<CubicCoordinate, Sector> getSectorsMap();

    /**
     * Gets the sector correspondent to a given coordinate.
     *
     * @param coord the coordinate to search for
     * @return if present, the sector, otherwise null
     */
    public Sector getSector(CubicCoordinate coord);

    /**
     * Gets a set containing all the coordinates of the sectors (of any kind) in
     * this StandardZone. We return a copy of the Set used in the Map
     * implementation to minimize mutability.
     *
     * @return the coordinates of this zone
     */
    public Set<CubicCoordinate> getCoordinates();

    /**
     * Gets the human sector. Throws an exception if the sector has not been
     * found or if more than one has been found. (A StandardZone should contain
     * exactly one HUMAN sector).
     *
     * @return the human sector
     */
    public Sector getHumanSector();

    /**
     * Gets the alien sector. Throws an exception if the sector has not been
     * found or if more than one has been found. (A StandardZone should contain
     * exactly one ALIEN sector).
     *
     * @return the alien sector
     */
    public Sector getAlienSector();

    /**
     * Gets all the hatch sectors contained in this zone. (A StandardZone should
     * contain at least one HATCH sector).
     *
     * @return the hatch sectors
     */
    public Set<Sector> getHatchSectors();

}
