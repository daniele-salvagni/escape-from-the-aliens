package it.polimi.ingsw.cg_2.utils.procedural;

import it.polimi.ingsw.cg_2.model.map.CubicCoordinate;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class contains the methods for processing grids with various algorithms.
 */
public class GridProcessor {

    /**
     * Gets all the cells of the grid with a given status.
     *
     * @param status the status to search for
     * @return the cells with the given status
     */
    public static Set<CubicCoordinate> findCellsWithStatus(
            Map<CubicCoordinate, CellStatus> grid, CellStatus status) {

        Set<CubicCoordinate> foundCells = new HashSet<CubicCoordinate>();

        // Loop trough the entire grid
        for (Map.Entry<CubicCoordinate, CellStatus> entry : grid.entrySet()) {

            // Check the status of the cell and add it to the found ones
            if (entry.getValue() == status) {
                foundCells.add(entry.getKey());
            }

        }

        return foundCells;

    }

}
