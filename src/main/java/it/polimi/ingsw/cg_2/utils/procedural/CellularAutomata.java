package it.polimi.ingsw.cg_2.utils.procedural;

import static it.polimi.ingsw.cg_2.utils.procedural.CellStatus.*;

import it.polimi.ingsw.cg_2.model.map.CubicCoordinate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CellularAutomata {

    // Default 23x14
    public static Map<CubicCoordinate, CellStatus> generateRectangularGrid(
            int width, int height) {

        Map<CubicCoordinate, CellStatus> grid = new HashMap<>();

        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                CubicCoordinate coord = CubicCoordinate
                        .createFromOddQ(col, row);
                grid.put(coord, DEAD);
            }

        }

        return grid;

    }

}
