package it.polimi.ingsw.cg_2.utils.procedural;

import it.polimi.ingsw.cg_2.model.map.CubicCoordinate;
import it.polimi.ingsw.cg_2.model.map.HexCalculator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    


    /**
     * Finds the biggest cluster within a certain grid. If there are two
     * clusters with the same size only the first one found by the algorithm
     * will be returned (the one with the most top-left cell).
     *
     * @param grid the grid where to search for the biggest cluster of cells
     * @param status the status of the cells of the clusters
     * @return a set containing the cells of the biggest found cluster
     */
    public static Set<CubicCoordinate> findBiggestCluster(
            Map<CubicCoordinate, CellStatus> grid, CellStatus status) {

        // This set keeps track of the unvisited cells with the given status
        Set<CubicCoordinate> unvisited = new HashSet<CubicCoordinate>();
        // This set keeps track of the biggest cluster of cells
        Set<CubicCoordinate> biggestCluster = new HashSet<CubicCoordinate>();

        // Copy all the cells with the given status into the 'unvisited' Set
        unvisited = findCellsWithStatus(grid, status);

        // Loop trough each cell of the grid
        for (Map.Entry<CubicCoordinate, CellStatus> entry : grid.entrySet()) {

            // If this cell has not already been visited
            if (unvisited.contains(entry.getKey())) {

                // Perform a flood fill to get all the cells of the cluster
                Set<CubicCoordinate> cluster = performUnlimitedFloodFill(grid,
                        entry.getKey(), status);

                // Remove all the visited cells
                unvisited.removeAll(cluster);

                // Check if the new cluster we found is bigger than the previous
                if (cluster.size() > biggestCluster.size()) {
                    biggestCluster = cluster;
                }

            }

        }

        return biggestCluster;

    }

    /**
     * Perform an unlimited flood fill, the algorithm will stop only when there
     * are no more cells clustered against the starting point.
     *
     * @param grid the grid where to perform the flood fill
     * @param start the starting point of the algorithm
     * @param status the status on which to perform the flood fill
     * @return a set containing all the found cells clustered against the
     *         starting point
     */
    public static Set<CubicCoordinate> performUnlimitedFloodFill(
            Map<CubicCoordinate, CellStatus> grid, CubicCoordinate start,
            CellStatus status) {

        return floodFill(grid, start, status, 0);

    }

    /**
     * Perform a limited flood fill, the algorithm will not go further the given
     * distance from the starting point.
     *
     * @param grid the grid where to perform the flood fill
     * @param start the starting point of the algorithm
     * @param status the status on which to perform the flood fill
     * @param distanceLimit the distance limit of the algorithm
     * @return a set containing all the found cells clustered against the
     *         starting point within a certain distance
     */
    public static Set<CubicCoordinate> performLimitedFloodFill(
            Map<CubicCoordinate, CellStatus> grid, CubicCoordinate start,
            CellStatus status, int distanceLimit) {

        return floodFill(grid, start, status, distanceLimit);

    }

    /**
     * Performs a flood fill algorithm on a grid from a certain starting point.
     * It returns all the cells clustered against the starting one.
     *
     * @param grid the grid where to perform the flood fill
     * @param start the starting point of the algorithm
     * @param status the status on which to perform the flood fill
     * @param distanceLimit the distance limit of the algorithm, if set to 0 it
     *            will continue until the cluster has been completely filled
     * @return a set containing all the found cells of the cluster
     */
    private static Set<CubicCoordinate> floodFill(
            Map<CubicCoordinate, CellStatus> grid, CubicCoordinate start,
            CellStatus status, int distanceLimit) {

        // If distanceLimit is equals to 0 we perform an unlimited flood fill
        boolean limited = distanceLimit != 0 ? false : true;

        // A temporary set of coordinates, it will contain all the cells
        // of this cluster
        Set<CubicCoordinate> cluster = new HashSet<>();

        // We add the starting cell to the cluster
        cluster.add(start);

        // This list will contain lists of cells with a certain distance
        // from the starting cell
        List<List<CubicCoordinate>> fringes = new ArrayList<List<CubicCoordinate>>();

        // We initialize the first element that will contain the cells
        // with distance 0 (the starting point)
        fringes.add(new ArrayList<CubicCoordinate>());

        // We add the starting cell also to the list of cells with
        // distance 0
        fringes.get(0).add(start);

        int distance = 1;
        // We loop until we filled an entire cluster, if the previous
        // iteration did not found any neighbor with the given status we
        // exit the loop. We also exit at a certain distance if the flood fill
        // is limited.
        while (!(fringes.get(fringes.size() - 1).isEmpty())
                || ((limited == true) && (distance <= distanceLimit))) {

            fringes.add(new ArrayList<CubicCoordinate>());

            // For each coordinate found in the previous iteration we
            // check if it has neighbors with the given status
            for (CubicCoordinate coord : fringes.get(distance - 1)) {

                // We try the 6 possible directions
                for (int d = 0; d < 6; d++) {

                    // We get the neighbor coordinate in the given
                    // direction
                    CubicCoordinate neighbor = HexCalculator.neighbor(coord, d);

                    // We check if the neighbor has the given status and
                    // if we did not already visited it
                    if (grid.get(neighbor) == status
                            && !cluster.contains(neighbor)) {

                        // Add it to the cluster
                        cluster.add(neighbor);
                        // Add it to the cells with a certain distance
                        // from the starting point
                        fringes.get(distance).add(neighbor);

                    }

                }

            }

            // Increase the distance for the next loop
            distance++;

        }

        return cluster;

    }

}
