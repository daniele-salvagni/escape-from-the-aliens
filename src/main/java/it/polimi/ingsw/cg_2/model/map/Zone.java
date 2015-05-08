package it.polimi.ingsw.cg_2.model.map;

import it.polimi.ingsw.cg_2.model.map.Sector.SectorType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

    /** A map containing all the sectors of the Zone. */
    private Map<CubicCoordinate, Sector> sectors;

    /**
     * Instantiates a new zone with a (@link Collection) of (@link Sector)s that
     * will be mapped by (@link CubicCoordinate).
     *
     * @param sectors the sectors to add to this zone
     */
    protected Zone(Collection<Sector> sectors) {

        this.sectors = new HashMap<CubicCoordinate, Sector>();

        for (Sector sector : sectors) {
            if (this.sectors.containsKey(sector.getCooridnate())) {
                throw new IllegalArgumentException(
                        "A Zone cannot contain two sectors in the same position.");
            } else {
                this.sectors.put(sector.getCooridnate(), sector);
            }
        }

    }

    /**
     * Gets all the sectors of the Zone mapped by CubicCoordinate. We return a
     * copy of the Map to minimize mutability.
     *
     * @return a new Map containing all the sectors of the zone
     */
    public Map<CubicCoordinate, Sector> getSectors() {
        Map<CubicCoordinate, Sector> newSectorsMap = new HashMap<>();
        newSectorsMap.putAll(sectors);
        return newSectorsMap;
    }

    /**
     * Gets the human sector. Throws an exception if the sector has not been
     * found or if more than one has been found. (a Zone should contain exactly
     * one HUMAN sector).
     *
     * @return the human sector
     */
    public Sector getHumanSector() {

        ArrayList<Sector> humanSectors = findSectorsFromType(SectorType.HUMAN);

        if (humanSectors.size() != 1) {
            throw new IllegalStateException(
                    "A Zone must contain exactly one human sector.");
        } else {
            return humanSectors.get(0);
        }

    }

    /**
     * Gets the alien sector. Throws an exception if the sector has not been
     * found or if more than one has been found. (a Zone should contain exactly
     * one ALIEN sector).
     *
     * @return the alien sector
     */
    public Sector getAlienSector() {

        ArrayList<Sector> alienSectors = findSectorsFromType(SectorType.ALIEN);

        if (alienSectors.size() != 1) {
            throw new IllegalStateException(
                    "A Zone must contain exactly one alien sector.");
        } else {
            return alienSectors.get(0);
        }

    }

    /**
     * Gets all the hatch sectors contained in this zone.
     *
     * @return the hatch sectors
     */
    public ArrayList<Sector> getHatchSectors() {

        ArrayList<Sector> hatchSectors = findSectorsFromType(SectorType.HATCH);

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
    private ArrayList<Sector> findSectorsFromType(SectorType sectorType) {

        ArrayList<Sector> foundSectors = new ArrayList<Sector>();

        for (Sector s : sectors.values()) {
            if (s.getType() == sectorType) {
                foundSectors.add(s);
            }
        }
        return foundSectors;

    }

}
