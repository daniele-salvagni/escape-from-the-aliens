package it.polimi.ingsw.cg_2.utils.procedural;

import static it.polimi.ingsw.cg_2.utils.procedural.CellStatus.*;
import it.polimi.ingsw.cg_2.model.map.CubicCoordinate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class CellularAutomata {

    private final int ALIVE_CHANCE = 45;

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

}
