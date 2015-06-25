package it.polimi.ingsw.cg_2.model.map;

import it.polimi.ingsw.cg_2.model.map.Sector.SectorType;
import it.polimi.ingsw.cg_2.utils.exception.InvalidZoneException;
import it.polimi.ingsw.cg_2.utils.map.MapIO;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A concrete factory for creating a new Zone by loading it form file, the
 * following is the valid color schema for this implementation, an invalid
 * color
 * in the Zone file will throw an InvalidZoneException.
 * <p/>
 * <ul>
 * <li>66CC66 (Light Green) - Secure Sector</li>
 * <li>009966 (Medium Green) - Dangerous Sector</li>
 * <li>993333 (Red) - Escape Hatch</li>
 * <li>993399 (Purple) - Alien Sector</li>
 * <li>0099CC (Cyan) - Human Sector</li>
 * <li>003333 (Dark Green) - EMPTY, no sectors</li>
 * </ul>
 */
public class ZoneLoader extends ZoneFactory {

    private static final Logger LOG = Logger.getLogger(ZoneLoader.class
            .getName());

    private static final int C_SAFE = 0xFF66CC66;
    private static final int C_DANGEROUS = 0xFF009966;
    private static final int C_HATCH = 0xFF993333;
    private static final int C_ALIEN = 0xFF993399;
    private static final int C_HUMAN = 0xFF0099CC;
    private static final int C_EMPTY = 0xFF003333;

    private static final Map<Integer, SectorType> COLOR_MAP;

    /**
     * A Static Initializer to map the colors with sector types. We want to
     * use an unmodifiableMap to keep it immutable.
     */
    static {
        Map<Integer, SectorType> modifiableMap = new HashMap<>();
        modifiableMap.put(C_SAFE, SectorType.SAFE);
        modifiableMap.put(C_DANGEROUS, SectorType.DANGEROUS);
        modifiableMap.put(C_HATCH, SectorType.HATCH);
        modifiableMap.put(C_ALIEN, SectorType.ALIEN);
        modifiableMap.put(C_HUMAN, SectorType.HUMAN);
        COLOR_MAP = Collections.unmodifiableMap(modifiableMap);
    }

    private final ZoneName zoneName;

    /**
     * Instantiates a new ZoneLoader.
     *
     * @param zoneName the name of the Zone containing its fileName
     */
    protected ZoneLoader(ZoneName zoneName) {

        this.zoneName = zoneName;

    }

    @Override
    public Zone createZone() {

        return new Zone(createSectors());

    }

    /**
     * Creates all the sectors of the Zone.
     *
     * @return the sets of Sectors
     */
    private Set<Sector> createSectors() {

        // This will contain all the loaded sectors
        Set<Sector> sectors = new HashSet<>();

        int[][] colorGrid;

        try {

            String filePath = ZoneLoader.class.getResource(
                    "/maps/" + zoneName.getFileName()).getFile();

            /* We try to load a Zone from file. It could throw an IOException
            . */
            colorGrid = MapIO.loadMap(filePath);

        } catch (IOException e) {

            /*
             * IO exception while loading the zone, it should not happen because
             * the user cannot directly insert a fileName but he can only choose
             * them from an enumeration. If there is an IOException then that
             * ZoneName is invalid and the user must select another one.
             */
            LOG.log(Level.SEVERE, "Error loading Zone: " + e.toString(), e);
            throw new InvalidZoneException(
                    "IO Exception while loading the Zone from file.");

        }

        /* The size of the colorGrid */
        int gridWidth = colorGrid.length;
        int gridHeight = colorGrid[0].length;

        for (int col = 0; col < gridWidth; col++) {
            for (int row = 0; row < gridHeight; row++) {

                // Create a new CubicCoordinate
                // // !IMPORTANT! we start form (0, 0) // //
                CubicCoordinate coord = CubicCoordinate
                        .createFromOddQ(col, row);

                // And use it to create a new Sector
                addSectorFromColor(sectors, colorGrid[col][row], coord);

            }
        }

        return sectors;

    }

    /**
     * Adds a new Sector to a sectors Set from a color (INT_ARGB).
     *
     * @param sectors the set where to add the new sector
     * @param color   the color representing the Sector Type
     * @param coord   the coordinate of the Sector
     */
    private void addSectorFromColor(Set<Sector> sectors, int color,
                                    CubicCoordinate coord) {

        if (color != C_EMPTY) { // If empty, we don't add any sector to the set
            if (COLOR_MAP.containsKey(color)) {
                sectors.add(createSector(coord, COLOR_MAP.get(color)));
            } else {
                    /* Invalid color, we could simply not create a sector but
                    this could lead to the creation of invalid maps: we
                    DON'T check the validity of a map loaded from file since
                    we presume it is correct, however if severe problems
                    are found based on the game rules the instantiation
                    will fail (at an higher level). */
                throw new InvalidZoneException(
                        "An invalid cell has been found in the zone File.");
            }
        }

    }

}
