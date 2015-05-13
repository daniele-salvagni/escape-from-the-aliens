package it.polimi.ingsw.cg_2.utils.procedural;

import static it.polimi.ingsw.cg_2.utils.procedural.CellStatus.*;
import it.polimi.ingsw.cg_2.model.map.CubicCoordinate;
import it.polimi.ingsw.cg_2.model.map.HexCalculator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class CellularAutomata {

    private static final List<CubicCoordinate> NEIGHBORHOOD = Arrays
            .asList(CubicCoordinate.create(1, -1, 0),
                    CubicCoordinate.create(1, 0, -1),
                    CubicCoordinate.create(0, 1, -1),
                    CubicCoordinate.create(-1, 1, 0),
                    CubicCoordinate.create(-1, 0, 1),
                    CubicCoordinate.create(0, -1, 1),
                    /* DIAGONALS */
                    CubicCoordinate.create(2, -1, -1),
                    CubicCoordinate.create(1, 1, -2),
                    CubicCoordinate.create(-1, 2, -1),
                    CubicCoordinate.create(-2, 1, 1),
                    CubicCoordinate.create(-1, -1, 2),
                    CubicCoordinate.create(1, -2, 1));

    private final int ALIVE_CHANCE = 45;

    private final int ALIVE_THRESHOLD = 6;
    private final int DEAD_THRESHOLD = 4; // Try 3, 4, 5?

    long seed;
    int gridWidth;
    int gridHeight;

    Random rand; // With SEED

    // To initialize as LinkedHashMap (we MUST preserve order)
    Map<CubicCoordinate, CellStatus> grid;

    // Default 23x14
    protected void generateRectangularGrid() {

        for (int col = 0; col < gridWidth; col++) {
            for (int row = 0; row < gridHeight; row++) {
                CubicCoordinate coord = CubicCoordinate
                        .createFromOddQ(col, row);
                grid.put(coord, DEAD);
            }

        }

    }

    protected void ceateInitialSeed() {

        for (Map.Entry<CubicCoordinate, CellStatus> cell : grid.entrySet()) {
            if (rand.nextInt(100) < ALIVE_CHANCE) {
                cell.setValue(ALIVE);
            }
        }

    }

    protected void applyOneStep() {

        for (Map.Entry<CubicCoordinate, CellStatus> cell : grid.entrySet()) {

            CubicCoordinate cellCoord = cell.getKey();
            CellStatus cellStatus = cell.getValue();

            int alive = countAliveNeighbors(cellCoord);
            int dead = NEIGHBORHOOD.size() - alive;

            if (cellStatus == ALIVE) {
                if (dead > ALIVE_THRESHOLD) {
                    cell.setValue(DEAD);
                }
            } else {
                if (dead < DEAD_THRESHOLD) {
                    cell.setValue(ALIVE);
                }
            }

        }

    }

    private int countAliveNeighbors(CubicCoordinate coord) {

        int alive = 0;

        for (CubicCoordinate n : NEIGHBORHOOD) {

            CubicCoordinate neighbor = HexCalculator.add(coord, n);
            if (grid.get(neighbor) == ALIVE) {
                // If the coordinate is outside the grid null is returned, so it
                // is will work as intended.
                alive++;
            }

        }

        return alive;

    }

}
