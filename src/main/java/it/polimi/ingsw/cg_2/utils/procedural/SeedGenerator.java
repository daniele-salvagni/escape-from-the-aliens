package it.polimi.ingsw.cg_2.utils.procedural;

import static it.polimi.ingsw.cg_2.model.map.CubicCoordinate.createFromOddQ;
import static it.polimi.ingsw.cg_2.utils.procedural.CellStatus.ALIVE;
import static it.polimi.ingsw.cg_2.utils.procedural.CellStatus.DEAD;
import it.polimi.ingsw.cg_2.model.map.CubicCoordinate;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * This class server as a helper for the CellularAutomata, it provides static
 * methods to generate seeds to be passed to the automata.
 **/
public class SeedGenerator {

    private static final int CELL_WIDTH = 23;
    private static final int CELL_HEIGHT = 14;

    private Random rand;

    /**
     * Generates a grid of cells with a rectangular shape.
     *
     * @param cellStatus the {@link CellStatus} of the cells
     * @return a map containing all the cells and their status
     */
    private Map<CubicCoordinate, CellStatus> generateRectangularGrid(
            CellStatus cellStatus) {

        Map<CubicCoordinate, CellStatus> rectGrid = new LinkedHashMap<>();

        for (int col = 0; col < CELL_WIDTH; col++) {
            for (int row = 0; row < CELL_HEIGHT; row++) {
                // It is easy with OddQ coordinates
                CubicCoordinate coord = createFromOddQ(col, row);
                rectGrid.put(coord, cellStatus);
            }

        }

        return rectGrid;

    }

}
