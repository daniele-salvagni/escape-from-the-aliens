package it.polimi.ingsw.cg_2.model.map;

import it.polimi.ingsw.cg_2.model.deck.SectorCard.SectorCardType;
import it.polimi.ingsw.cg_2.model.map.Sector.SectorType;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Zone {
    Map<CubicCoordinate, Sector> sectors;

    // TODO: Incomplete

    public Map<CubicCoordinate, Sector> getSectors() {
        return sectors;
    }

    public Sector getHumanSector() {
        Sector humanSector = findSectorFromType(SectorType.HUMAN);
        if (humanSector == null) {
            throw new NullPointerException(
                    "A Zone must contain an human sector.");
        } else {
            return humanSector;
        }
    }

    public Sector getAlienSector() {
        Sector alienSector = findSectorFromType(SectorType.ALIEN);
        if (alienSector == null) {
            throw new NullPointerException(
                    "A Zone must contain an alien sector.");
        } else {
            return alienSector;
        }
    }

    public ArrayList<Sector> getHatchSectors() {
        ArrayList<Sector> hatchSectors = findSectorsFromType(SectorType.HATCH);
        if (hatchSectors.isEmpty()) {
            throw new NullPointerException(
                    "A Zone must contain at least one escape hatch.");
        } else {
            return hatchSectors;
        }
    }

    private Sector findSectorFromType(SectorType sectorType) {
        for (Sector s : sectors.values()) {
            if (s.getType() == sectorType) {
                return s;
            }
        }
        return null;
    }

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
