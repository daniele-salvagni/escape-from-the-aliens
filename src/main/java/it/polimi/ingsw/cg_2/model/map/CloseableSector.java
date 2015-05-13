package it.polimi.ingsw.cg_2.model.map;

/**
 * This class extends a standard {@link Sector} with the possibility to be open
 * or closed, it the main implementation of this game the Hatch Sector will be
 * the only one to use this behavior, however it would be conceptually wrong to
 * call this class "HatchSector" because the type is already the attribute of
 * the class, we are just adding a behavior.
 */
public class CloseableSector extends Sector {

    private boolean open;

    /**
     * Instantiates a new CloseableSector with the default status open.
     *
     * @param position the position relative to a {@link StandardZone}
     * @param type the type of the Sector
     */
    public CloseableSector(CubicCoordinate position, SectorType type) {

        super(position, type);
        open = true;

    }

    /**
     * Sets the Sector status to open.
     */
    public void open() {

        open = true;

    }

    /**
     * Sets the Sector status to closed.
     */
    public void close() {

        open = false;

    }

    /**
     * Checks if the Sector is open or closed.
     *
     * @return true, if the sector is open
     */
    public boolean isOpen() {

        return open;

    }
}
