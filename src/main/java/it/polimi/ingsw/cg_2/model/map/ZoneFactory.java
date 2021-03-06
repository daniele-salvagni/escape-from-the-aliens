package it.polimi.ingsw.cg_2.model.map;

import it.polimi.ingsw.cg_2.model.map.Sector.SectorType;

/**
 * An abstract factory for creating Zones, it can be implemented to create them
 * in different ways (like loading it from a file or generating them
 * procedurally).
 */
public abstract class ZoneFactory {

    /**
     * Create a new {@link ZoneLoader} instance, the returned factory will
     * create a {@link Zone} by loading it from the provided zone name.
     *
     * @param zoneName the name of the zone to be loaded
     * @return a new ZoneFactory (ZoneLoader)
     */
    public static ZoneFactory newLoader(ZoneName zoneName) {

        return new ZoneLoader(zoneName);

    }

    // TODO: Add only when the generator is complete!
    /*
     * public static final ZoneFactory newGenerator(RandType type, int
     * hatchNumber, long seed) {
     * 
     * return new ZoneGenerator(type, hatchNumber, seed);
     * 
     * }
     */

    /**
     * Creates a Zone based on the parameters given when creating the factory.
     *
     * @return a new Zone
     */
    public abstract Zone createZone();

    /**
     * Creates a new {@link Sector}.
     *
     * @param type  the type of the Sector
     * @param coord the CubicCoordinate of the Sector
     * @return the new Sector
     */
    protected Sector createSector(CubicCoordinate coord, SectorType type) {

        if (type == SectorType.HATCH) {

            /*
             * Currently in all the existing implementations every HATCH sector
             * must be a CloseableSector.
             */
            return new CloseableSector(coord, type);

        } else {

            return new Sector(coord, type);

        }

    }

}
