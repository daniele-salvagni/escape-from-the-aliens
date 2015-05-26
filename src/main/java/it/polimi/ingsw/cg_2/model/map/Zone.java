package it.polimi.ingsw.cg_2.model.map;

import it.polimi.ingsw.cg_2.model.map.Sector.SectorType;
import it.polimi.ingsw.cg_2.utils.exception.InvalidZoneException;

import java.util.*;

/**
 * This class represents a Zone (the map of this game), this zone should
 * contain:
 * <ul>
 * <li>Exactly one HUMAN Sector.</li>
 * <li>Exactly one ALIEN Sector.</li>
 * <li>At least one HATCH sector.</li>
 * </ul>
 */
public class Zone {

    /**
     * A map containing all the sectors of the Zone. A Sector also contain its
     * coordinate but we provide this for faster access.
     */
    private final Map<CubicCoordinate, Sector> sectorMap;

    /**
     * Instantiates a new zone with a {@link Collection} of {@link Sector}s
     * that
     * will be mapped by {@link CubicCoordinate}.
     *
     * @param sectors the sectors to add to this zone
     */
    protected Zone(Set<Sector> sectors) {

        sectorMap = new HashMap<>();

        for (Sector sector : sectors) {
            if (sectorMap.containsKey(sector.getCooridnate())) {
                throw new InvalidZoneException(
                        "A Zone cannot contain two sectors in the same " +
                                "position.");
            } else {
                sectorMap.put(sector.getCooridnate(), sector);
            }
        }

    }

    /**
     * Gets all the sectors of the Zone mapped by CubicCoordinate. We return a
     * {@link Collections#unmodifiableMap(Map)} to reduce mutability.
     *
     * @return a new Map containing all the sectors of the zone
     */
    public Map<CubicCoordinate, Sector> getSectorsMap() {

        return Collections.unmodifiableMap(sectorMap);

    }

    /**
     * Gets the sector correspondent to a given coordinate.
     *
     * @param coord the coordinate to search for
     * @return if present, the sector, otherwise null
     */
    public Sector getSector(CubicCoordinate coord) {

        return sectorMap.get(coord);

    }

    /**
     * Gets a set containing all the coordinates of the sectors (of any kind)
     * in this Zone. It is returned a {@link Collections#unmodifiableSet(Set)}
     * to reduce mutability.
     *
     * @return the coordinates of this zone
     */
    public Set<CubicCoordinate> getCoordinates() {

        /*
         * The set returned by the keySet() method is backed by the map, so
         * changes to the map are reflected in the set, and vice-versa. For this
         * reason we return an unmodifiableSet reduce mutability.
         */
        return Collections.unmodifiableSet(sectorMap.keySet());

    }

    /**
     * Gets the human sector. Throws an exception if the sector has not been
     * found or if more than one has been found. (A Zone should contain exactly
     * one HUMAN sector).
     *
     * @return the human sector
     */
    public Sector getHumanSector() {

        Set<Sector> humanSectors = findSectorsFromType(SectorType.HUMAN);

        if (humanSectors.size() != 1) {
            throw new IllegalStateException(
                    "A Zone must contain exactly one human sector.");
        } else {
            return humanSectors.iterator().next();
        }

    }

    /**
     * Gets the alien sector. Throws an exception if the sector has not been
     * found or if more than one has been found. (A Zone should contain exactly
     * one ALIEN sector).
     *
     * @return the alien sector
     */
    public Sector getAlienSector() {

        Set<Sector> alienSectors = findSectorsFromType(SectorType.ALIEN);

        if (alienSectors.size() != 1) {
            throw new IllegalStateException(
                    "A Zone must contain exactly one alien sector.");
        } else {
            return alienSectors.iterator().next();
        }

    }

    /**
     * Gets all the hatch sectors contained in this zone. (A Zone should
     * contain
     * at least one HATCH sector).
     *
     * @return the hatch sectors
     */
    public Set<Sector> getHatchSectors() {

        Set<Sector> hatchSectors = findSectorsFromType(SectorType.HATCH);

        if (hatchSectors.isEmpty()) {
            throw new IllegalStateException(
                    "A Zone must contain at least one escape hatch.");
        } else {
            return hatchSectors;
        }

    }

    /**
     * Find all the sectors in the Zone of a certain type.
     *
     * @param sectorType the sector type to be searched for
     * @return an ArrayList containing the sectors found
     */
    private Set<Sector> findSectorsFromType(SectorType sectorType) {

        Set<Sector> foundSectors = new HashSet<>();

        for (Sector s : sectorMap.values()) {
            if (s.getType() == sectorType) {
                foundSectors.add(s);
            }
        }
        return foundSectors;

    }

}
