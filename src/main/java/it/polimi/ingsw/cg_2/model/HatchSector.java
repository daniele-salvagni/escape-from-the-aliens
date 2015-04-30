package it.polimi.ingsw.cg_2.model;

/**
 * This class extends a standard (@link Sector) with the possibility to have a
 * status.
 */
public class HatchSector extends Sector {

    public static enum SectorStatus {
        OPEN, CLOSED;
    }

    private SectorStatus sectorStatus;

    /**
     * Instantiates a new HatchSector with the default status of Status.OPEN.
     *
     * @param position the position relative to a (@link Zone)
     * @param type the type of the Sector
     */
    public HatchSector(CubicCoordinate position, SectorType type) {
        super(position, type);
        sectorStatus = SectorStatus.OPEN;
    }

    /**
     * Sets the Sector status to Status.OPEN.
     */
    public void open() {
        sectorStatus = SectorStatus.OPEN;
    }

    /**
     * Sets the Sector status to Status.CLOSED.
     */
    public void close() {
        sectorStatus = SectorStatus.CLOSED;
    }

    /**
     * Gets the Sector status.
     *
     * @return the Sector status
     */
    public SectorStatus getStatus() {
        return sectorStatus;
    }
}
