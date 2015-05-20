package it.polimi.ingsw.cg_2.model.map;

import it.polimi.ingsw.cg_2.model.map.Sector.SectorType;
import it.polimi.ingsw.cg_2.utils.exception.InvalidZoneException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
     * Instantiates a new zone with a {@link Collection} of {@link Sector}s that
     * will be mapped by {@link CubicCoordinate}.
     *
     * @param sectors the sectors to add to this zone
     */
    protected Zone(Set<Sector> sectors) {

        sectorMap = new HashMap<CubicCoordinate, Sector>();

        for (Sector sector : sectors) {
            if (sectorMap.containsKey(sector.getCooridnate())) {
                throw new InvalidZoneException(
                        "A Zone cannot contain two sectors in the same position.");
            } else {
                sectorMap.put(sector.getCooridnate(), sector);
            }
        }

    }

    /**
     * Gets all the sectors of the Zone mapped by CubicCoordinate. We return a
     * copy of the Map to minimize mutability.
     *
     * @return a new Map containing all the sectors of the zone
     */
    public Map<CubicCoordinate, Sector> getSectorsMap() {

        /*
         * Effective Java - Item 11: it is better not to use clone(). The copy
         * constructor, however, is not defined for the Map interface but only
         * for some implementations.
         */
        Map<CubicCoordinate, Sector> newSectorsMap = new HashMap<>();
        newSectorsMap.putAll(sectorMap);
        return newSectorsMap;

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
     * Gets a set containing all the coordinates of the sectors (of any kind) in
     * this Zone. We return a copy of the Set used in the Map implementation to
     * minimize mutability.
     *
     * @return the coordinates of this zone
     */
    public Set<CubicCoordinate> getCoordinates() {

        /*
         * The set returned by the keySet() method is backed by the map, so
         * changes to the map are reflected in the set, and vice-versa. For this
         * reason we return a copy of the set to reduce mutability.
         */
        Set<CubicCoordinate> newCoordSet = new HashSet<>();
        newCoordSet.addAll(sectorMap.keySet());
        return newCoordSet;

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
     * Gets all the hatch sectors contained in this zone. (A Zone should contain
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

        Set<Sector> foundSectors = new HashSet<Sector>();

        for (Sector s : sectorMap.values()) {
            if (s.getType() == sectorType) {
                foundSectors.add(s);
            }
        }
        return foundSectors;

    }

}
