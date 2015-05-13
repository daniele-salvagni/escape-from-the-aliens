package it.polimi.ingsw.cg_2.utils.procedural;

import it.polimi.ingsw.cg_2.model.map.CubicCoordinate;

import java.util.HashSet;
import java.util.Set;

public class CellularAutomata {

    // Default 23x14
    public static Set<CubicCoordinate> generateRectangularGrid(int width,
            int height) {

        Set<CubicCoordinate> grid = new HashSet<>();

        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                CubicCoordinate coord = CubicCoordinate
                        .createFromOddQ(col, row);
                grid.add(coord);
            }

        }

        return grid;

    }

}
