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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((position == null) ? 0 : position.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sector other = (Sector) obj;
        if (position == null) {
            if (other.position != null)
                return false;
        } else if (!position.equals(other.position))
            return false;
        if (type != other.type)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Sector [type=" + type + ", position=" + position + "]";
    }

}
