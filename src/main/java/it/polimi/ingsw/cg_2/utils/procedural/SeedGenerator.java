package it.polimi.ingsw.cg_2.utils.procedural;

import static it.polimi.ingsw.cg_2.model.map.CubicCoordinate.createFromOddQ;
import it.polimi.ingsw.cg_2.model.map.CubicCoordinate;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * This class server as a helper for the CellularAutomata, it provides static
 * methods to generate seeds to be passed to the automata.
 **/
public class SeedGenerator {

    /* They can be also used as parameters, everything will work with any size. */
    private static final int GRID_WIDTH = 23;
    private static final int GRID_HEIGHT = 14;

    /* The size of the central altered area for the altered seed. */
    private static final int INNER_WIDTH = 8;
    private static final int INNER_HEIGHT = 6;

    /**
     * Suppress the default constructor for noninstantiability.
     */
    private SeedGenerator() {

        throw new AssertionError();
    }

    /**
     * Generates a grid of cells with a rectangular shape.
     *
     * @param cellStatus the {@link CellStatus} of the cells
     * @return a map containing all the cells and their status
     */
    private static Map<CubicCoordinate, CellStatus> generateRectangularGrid(
            CellStatus cellStatus) {

        Map<CubicCoordinate, CellStatus> rectGrid = new LinkedHashMap<>();

        for (int col = 0; col < GRID_WIDTH; col++) {
            for (int row = 0; row < GRID_HEIGHT; row++) {
                // It is easy with OddQ coordinates
                CubicCoordinate coord = createFromOddQ(col, row);
                rectGrid.put(coord, cellStatus);
            }

        }

        return rectGrid;

    }

    /**
     * Creates the initial seed of cells with a given status, the seed is
     * altered to have a different distribution in the central part of the grid,
     * having an higher concentration of cells helps obtaining a more centered
     * and ordered map.
     *
     * @param bornChance the born chance
     * @param alteredChance the altered chance in the center of the seed, if -1
     *            the seed will not be altered (0-100)
     * @param alive the 'alive' status
     * @param dead the 'dead' status
     * @param seed the seed used for the generation of pseudo random numbers, if
     *            -1 the seed will be chosen randomly.
     * @return the generated seed
     */
    public static Map<CubicCoordinate, CellStatus> generateAlteredSeed(
            int bornChance, int alteredChance, CellStatus alive,
            CellStatus dead, long seed) {

        Map<CubicCoordinate, CellStatus> grid = generateRectangularGrid(dead);

        Random rand = new Random();
        if (seed != -1) {
            rand.setSeed(seed);
        }

        for (Map.Entry<CubicCoordinate, CellStatus> cell : grid.entrySet()) {

            if ((alteredChance != -1)
                    && (Math.abs(GRID_WIDTH / 2 - cell.getKey().getOddQCol()) * 2 < INNER_WIDTH)
                    && (Math.abs(GRID_HEIGHT / 2 - cell.getKey().getOddQRow()) * 2 < INNER_HEIGHT)) {

                // Different chance in the middle of the grid
                if (rand.nextInt(100) < alteredChance) {
                    cell.setValue(alive);
                }

            } else {

                // Default chance elsewhere
                if (rand.nextInt(100) < bornChance) {
                    cell.setValue(alive);
                }

            }

        }

        return grid;

    }

}
