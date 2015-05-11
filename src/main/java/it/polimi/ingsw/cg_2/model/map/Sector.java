package it.polimi.ingsw.cg_2.model.map;

/**
 * This class represents a Sector, it has a {@link CubicCoordinate} relative to
 * a {@link Zone} and a type. This class is immutable.
 */
public class Sector {

    /**
     * The kinds of Sector type.
     */
    public static enum SectorType {
        SAFE,
        DANGEROUS,
        HUMAN,
        ALIEN,
        HATCH;
    }

    private SectorType type;
    private CubicCoordinate position;

    /**
     * Instantiates a new sector.
     *
     * @param position the position relative to a {@link Zone}
     * @param type the type of the sector
     */
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
     * Gets the sector coordinate relative to the {@link Zone}.
     *
     * @return the sector coordinate.
     */
    public CubicCoordinate getCooridnate() {
        return position;
    }

    @Override
    public String toString() {
        return "Sector [type=" + type + ", position=" + position + "]";
    }

}
