package it.polimi.ingsw.cg_2.model.map;

/**
 * This class extends a standard (@link Sector) with the possibility to be open
 * or closed, it the main implementation the Hatch Sector will be the only one
 * to use this behavior, however it would be conceptually wrong to call this
 * class "HatchSector" because the type is already the attribute of the class,
 * we are just adding a behavior.
 */
public class CloseableSector extends Sector {

    public static enum SectorStatus {
        OPEN, CLOSED;
    }

    private SectorStatus sectorStatus;

    /**
     * Instantiates a new CloseableSector with the default status of Status.OPEN.
     *
     * @param position the position relative to a (@link Zone)
     * @param type the type of the Sector
     */
    public CloseableSector(CubicCoordinate position, SectorType type) {
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
