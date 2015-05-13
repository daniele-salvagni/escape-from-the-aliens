package it.polimi.ingsw.cg_2.model.map;

import it.polimi.ingsw.cg_2.model.map.Sector.SectorType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class represents a standard implementation of a {@link Zone}.
 */
public class StandardZone implements Zone {

    /**
     * A map containing all the sectors of the StandardZone. A Sector also
     * contain its coordinate but we provide this map for faster access.
     */
    private final Map<CubicCoordinate, Sector> sectorMap;

    /**
     * Instantiates a new zone with a {@link Collection} of {@link Sector}s that
     * will be mapped by {@link CubicCoordinate}.
     *
     * @param sectors the sectors to add to this zone
     */
    protected StandardZone(Set<Sector> sectors) {

        sectorMap = new HashMap<CubicCoordinate, Sector>();

        for (Sector sector : sectors) {
            if (sectorMap.containsKey(sector.getCooridnate())) {
                throw new IllegalArgumentException(
                        "A StandardZone cannot contain two sectors in the same position.");
            } else {
                sectorMap.put(sector.getCooridnate(), sector);
            }
        }

    }

    @Override
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

    @Override
    public Sector getSector(CubicCoordinate coord) {

        return sectorMap.get(coord);

    }

    @Override
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

    @Override
    public Sector getHumanSector() {

        Set<Sector> humanSectors = findSectorsFromType(SectorType.HUMAN);

        if (humanSectors.size() != 1) {
            throw new IllegalStateException(
                    "A StandardZone must contain exactly one human sector.");
        } else {
            return humanSectors.iterator().next();
        }

    }

    @Override
    public Sector getAlienSector() {

        Set<Sector> alienSectors = findSectorsFromType(SectorType.ALIEN);

        if (alienSectors.size() != 1) {
            throw new IllegalStateException(
                    "A StandardZone must contain exactly one alien sector.");
        } else {
            return alienSectors.iterator().next();
        }

    }

    @Override
    public Set<Sector> getHatchSectors() {

        Set<Sector> hatchSectors = findSectorsFromType(SectorType.HATCH);

        if (hatchSectors.isEmpty()) {
            throw new IllegalStateException(
                    "A StandardZone must contain at least one escape hatch.");
        } else {

            return hatchSectors;
        }

    }

    /**
     * Find all the sectors in the StandardZone of a certain type.
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
