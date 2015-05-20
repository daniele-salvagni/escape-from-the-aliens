package it.polimi.ingsw.cg_2.model.map;

import it.polimi.ingsw.cg_2.model.map.Sector.SectorType;
import it.polimi.ingsw.cg_2.utils.exception.InvalidZoneException;
import it.polimi.ingsw.cg_2.utils.map.MapIO;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/* A concrete factory for creating a new Zone by loading it form file,  */
public class ZoneLoader extends ZoneFactory {

    private static final Logger log = Logger.getLogger(ZoneLoader.class
            .getName());

    /*-
     * Valid color schema for this ZoneFactory implementation.
     * 
     * | Color                   | Sector             |
     * | ----------------------- | ------------------ |
     * | 66CC66 (Light Green)    | Secure Sector      |
     * | 009966 (Medium Green)   | Dangerous Sector   |
     * | 993333 (Red)            | Escape Hatch       |
     * | 993399 (Purple)         | Alien Sector       |
     * | 0099CC (Cyan)           | Human Sector       |
     * | 003333 (Dark Green)     | EMPTY, no sectors  |
     */

    private static final int C_SAFE = 0x0066CC66;
    private static final int C_DANGEROUS = 0x00009966;
    private static final int C_HATCH = 0x00993333;
    private static final int C_ALIEN = 0x00993399;
    private static final int C_HUMAN = 0x000099CC;
    private static final int C_EMPTY = 0x00003333;

    private final ZoneName zoneName;
    private final Set<Sector> sectors;

    /**
     * Instantiates a new ZoneLoader.
     *
     * @param zoneName the name of the Zone containing its fileName
     */
    protected ZoneLoader(ZoneName zoneName) {

        this.zoneName = zoneName;
        sectors = new HashSet<>();

    }

    /*
     * (non-Javadoc)
     * 
     * @see it.polimi.ingsw.cg_2.model.map.ZoneFactory#createZone()
     */
    @Override
    public Zone createZone() {

        return new Zone(createSectors());

    }

    /**
     * Creates the sectors.
     *
     * @return the sets the
     */
    private Set<Sector> createSectors() {

        int[][] colorGrid;

        try {

            /* We try to load a Zone from file. It could throw an IOException. */
            colorGrid = MapIO.loadMap("maps/" + zoneName.getFileName());

        } catch (IOException e) {

            /*
             * IO exception while loading the zone, it should not happen because
             * the user cannot directly insert a fileName but he can only choose
             * them from an enumeration. If there is an IOException then that
             * ZoneName is invalid and the user must select another one.
             */
            log.log(Level.SEVERE, e.toString(), e);
            throw new InvalidZoneException(
                    "IO Exception while loading the Zone from file.");

        }

        /* The size of the colorGrid */
        int gridHeight = colorGrid.length;
        int gridWidth = colorGrid[0].length;

        for (int col = 0; col < gridWidth; col++) {
            for (int row = 0; row < gridHeight; row++) {

                // Create a new CubicCoordinate
                CubicCoordinate coord = CubicCoordinate
                        .createFromOddQ(col, row);

                // And use it to create a new Sector
                addSectorFromColor(colorGrid[col][row], coord);

            }
        }

        return sectors;

    }

    /**
     * Creates the sector from color.
     *
     * @param color the color
     * @param coord the coord
     * @return the sector
     */
    private void addSectorFromColor(int color, CubicCoordinate coord) {

        switch (color) {

        case C_SAFE:
            sectors.add(createSector(coord, SectorType.SAFE));
            break;

        case C_DANGEROUS:
            sectors.add(createSector(coord, SectorType.DANGEROUS));
            break;

        case C_HATCH:
            sectors.add(createSector(coord, SectorType.HATCH));
            break;

        case C_ALIEN:
            sectors.add(createSector(coord, SectorType.ALIEN));
            break;

        case C_HUMAN:
            sectors.add(createSector(coord, SectorType.HUMAN));
            break;

        case C_EMPTY:
            // Empty, we don't create any sector
            break;

        default:

            /*
             * Invalid color, we could simply not create a sector but this could
             * lead to the creation of invalid maps: we DON'T check the validity
             * of a map loaded from file since we presume it is correct, however
             * if severe problems are found based on the game rules the
             * instantiation will fail (at an higher level).
             */
            throw new InvalidZoneException(
                    "An invalid cell has been found in the zone File.");

        }

    }

}
