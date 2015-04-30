package it.polimi.ingsw.cg_2.model.map;

/**
 * This class represents a Sector, it has a position relative to a (@link Zone)
 * and a type.
 */
public class Sector {

    /**
     * The kinds of Sector type.
     */
    public static enum SectorType {
        SAFE, DANGEROUS, HUMAN, ALIEN, HATCH;
    }

    private SectorType type;
    private CubicCoordinate position;

    public Sector(CubicCoordinate position, SectorType type) {
        this.position = position;
        this.type = type;
    }

    /**
     * Gets the type of the Sector.
     *
     * @return the type of the Sector
     */
    public SectorType getType() {
        return type;
    }

    /**
     * Gets the secotor coordinate relative to the (@link Zone).
     *
     * @return the secotor cooridnate.
     */
    public CubicCoordinate getCooridnate() {
        return position;
    }

}
