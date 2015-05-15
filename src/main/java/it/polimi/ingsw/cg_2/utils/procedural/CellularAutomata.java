package it.polimi.ingsw.cg_2.utils.procedural;

import static it.polimi.ingsw.cg_2.utils.procedural.CellStatus.*;
import static it.polimi.ingsw.cg_2.model.map.CubicCoordinate.*;
import it.polimi.ingsw.cg_2.model.map.CubicCoordinate;
import it.polimi.ingsw.cg_2.model.map.CubicCoordinateTest;
import it.polimi.ingsw.cg_2.model.map.HexCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class CellularAutomata {

    /* The colors we use to print our results */
    private static final int DARK_GREEN = 0x003333; // Dead
    private static final int LIGHT_GREEN = 0x66CC66; //
    private static final int MEDIUM_GREEN = 0x009966;

    /* We use a sort of "Moore Neighborhood" extended to hexagons. */
    private static final List<CubicCoordinate> NEIGHBORHOOD = Arrays.asList(
            create(1, -1, 0), create(1, 0, -1), create(0, 1, -1),
            create(-1, 1, 0), create(-1, 0, 1), create(0, -1, 1),
            /* DIAGONALS */
            create(2, -1, -1), create(1, 1, -2), create(-1, 2, -1),
            create(-2, 1, 1), create(-1, -1, 2), create(1, -2, 1));

    // Pseudo random
    Random rand;
    private long seed;

    // Seed
    private final int INNER_SEED_CHANCE = 85;
    private final int INNER_WIDTH = 8;
    private final int INNER_HEIGHT = 6;
    private final int seedChance; // Default seed

    // Tick configuration
    private int birthThreshold;
    private int deathThreshold;

    // Grid size
    private final int gridWidth;
    private final int gridHeight;

    private Map<CubicCoordinate, CellStatus> gridState;

    /**
     * Instantiates a new cellular automata.
     *
     * @param birthThreshold the birth threshold
     * @param deathThreshold the death threshold
     * @param seedChance the seed chance
     * @param seed the seed, 0 for random seed
     */
    public CellularAutomata(int birthThreshold, int deathThreshold,
            int seedChance, long seed) {

        this.birthThreshold = birthThreshold;
        this.deathThreshold = deathThreshold;

        this.seed = seed;
        this.gridWidth = 23;
        this.gridHeight = 14;

        // Linked, me must preserve order
        this.gridState = new LinkedHashMap<>();

        this.seedChance = seedChance;

        if (seed != 0) {
            this.rand = new Random(seed);
        } else {
            this.rand = new Random();
        }

    }

    public void doThings() {

        generateRectangularGrid();
        generateAlteredSeed();

        applyTick();
        applyTick();
        applyTick();

        keepBiggestCluster(ALIVE);

        // createSafeSeed();

        // safeSectors();
        // safeSectors();
        // safeSectors();

    }

    // -----------------------------------------------------------------------------

    protected void createSafeSeed() {

        for (Map.Entry<CubicCoordinate, CellStatus> cell : gridState.entrySet()) {

            if (rand.nextInt(100) < 23) {
                cell.setValue(SAFE);
            }

            /*
             * if (rand.nextInt(100) < ALIVE_CHANCE) { cell.setValue(ALIVE); }
             */

        }

    }

    public void safeSectors() {

        /*
         * We need to work on a copy of the grid, the next state should depend
         * only by the previous one, and not the current one during the
         * iteration.
         */
        // Map<CubicCoordinate, CellStatus> newGrid = new LinkedHashMap<>();
        // newGrid.putAll(grid);

        for (Map.Entry<CubicCoordinate, CellStatus> cell : gridState.entrySet()) {

            CubicCoordinate cellCoord = cell.getKey();
            CellStatus cellStatus = cell.getValue();

            /* The count is made on the old grid (the previous automaton tick) */
            int alive = countAliveNeighbors(this.gridState, cellCoord);
            int safe = countSafeNeighbors(cellCoord);

            if (cellStatus == ALIVE) {
                if (((alive > 11) && (safe < 1))) {
                    cell.setValue(SAFE);
                }
            } else if (cellStatus == SAFE) {
                if (((safe > 2) && (alive < 2)) || (safe < 3)) {
                    cell.setValue(ALIVE);
                }
            }

        }

        // Update the grid status
        // grid = newGrid; // Update the grid status
    }

    private int countSafeNeighbors(CubicCoordinate cell) {

        int alive = 0;

        // Loop trough each neighbor and check if it is alive:
        for (CubicCoordinate n : NEIGHBORHOOD) {
            CubicCoordinate neighbor = HexCalculator.add(cell, n);
            if (gridState.get(neighbor) == SAFE) {
                /*
                 * If the coordinate is outside the grid map.get(..) returns
                 * null, so it will work as intended. We consider not existing
                 * cells (outside the rectangular grid) as dead.
                 */
                alive++;
            } else if (gridState.get(neighbor) == null) {
                alive += 3;
            }
        }

        return alive;

    }

    // -----------------------------------------------------------------------------

    public int[][] getPixelRepresentation() {

        int[][] pixelMatirx = new int[gridWidth][gridHeight];

        for (Map.Entry<CubicCoordinate, CellStatus> cell : gridState.entrySet()) {

            if (cell.getValue() == ALIVE) {
                pixelMatirx[cell.getKey().getOddQCol()][cell.getKey()
                        .getOddQRow()] = MEDIUM_GREEN;
            } else if (cell.getValue() == DEAD) {
                pixelMatirx[cell.getKey().getOddQCol()][cell.getKey()
                        .getOddQRow()] = DARK_GREEN;
            } else if (cell.getValue() == SAFE) {
                pixelMatirx[cell.getKey().getOddQCol()][cell.getKey()
                        .getOddQRow()] = LIGHT_GREEN;
            }

        }

        return pixelMatirx;

    }

    /**
     * Generates a grid of dead cells with a rectangular shape.
     */
    private void generateRectangularGrid() {

        for (int col = 0; col < gridWidth; col++) {
            for (int row = 0; row < gridHeight; row++) {
                CubicCoordinate coord = createFromOddQ(col, row);
                gridState.put(coord, DEAD);
            }

        }

    }

    /**
     * Creates the initial seed of ALIVE cells, the seed is altered to have a
     * different distribution in the central part of the grid, having an higher
     * concentration of alive cells helps obtaining a more centered and ordered
     * map.
     */
    private void generateAlteredSeed() {

        for (Map.Entry<CubicCoordinate, CellStatus> cell : gridState.entrySet()) {

            if ((Math.abs(gridWidth / 2 - cell.getKey().getOddQCol()) * 2 < INNER_WIDTH)
                    && (Math.abs(gridHeight / 2 - cell.getKey().getOddQRow()) * 2 < INNER_HEIGHT)) {

                // Different chance in the middle of the grid
                if (rand.nextInt(100) < INNER_SEED_CHANCE) {
                    cell.setValue(ALIVE);
                }

            } else {

                // Default chance elsewhere
                if (rand.nextInt(100) < seedChance) {
                    cell.setValue(ALIVE);
                }

            }

        }

    }

    /**
     * Executes a single tick of the cellular automata, it is applied to the
     * whole grid simultaneously.
     * 
     */
    private void applyTick() {

        /*
         * We need to work on a copy of the grid, the next state should depend
         * only by the previous one, and not the current one during the
         * iteration.
         */
        Map<CubicCoordinate, CellStatus> tempGrid = new LinkedHashMap<>();
        tempGrid.putAll(gridState);

        // We loop trough the
        for (Map.Entry<CubicCoordinate, CellStatus> cell : tempGrid.entrySet()) {

            CubicCoordinate cellCoord = cell.getKey();
            CellStatus cellStatus = cell.getValue();

            /* The count is made on the old grid (the previous automaton tick) */
            int alive = countAliveNeighbors(this.gridState, cellCoord);

            if (cellStatus == DEAD) {
                if ((alive < birthThreshold)) {
                    cell.setValue(ALIVE);
                }
            } else if (cellStatus == ALIVE) {
                if ((alive < deathThreshold)) {
                    cell.setValue(DEAD);
                }
            }

        }

        gridState = tempGrid; // Update the grid status

    }

    /**
     * Count the number of alive neighbors of a given cell in a given grid.
     *
     * @param grid the grid on which to do the count
     * @param cell the cell of the grid
     * @return the number of alive neighbors
     */
    private int countAliveNeighbors(Map<CubicCoordinate, CellStatus> grid,
            CubicCoordinate cell) {

        int alive = 0;

        // Loop trough each neighbor and check if it is alive:
        for (CubicCoordinate shift : NEIGHBORHOOD) {
            CubicCoordinate neighbor = HexCalculator.add(cell, shift);
            if (grid.get(neighbor) == ALIVE) {
                /*
                 * If the coordinate is outside the grid map.get(..) returns
                 * null, so it will work as intended. We consider not existing
                 * cells (outside the rectangular grid) as dead.
                 */
                alive++;
            } else if (grid.get(neighbor) == null) {
                alive += 3;
            }
        }

        return alive;

    }

    /**
     * Finds the biggest cluster of cells with a given status, it does use a
     * flood fill algorithm. Everything else is discarded.
     */
    private void keepBiggestCluster(CellStatus status) {

        // This set keeps track of the unvisited cells with the given status
        Set<CubicCoordinate> unvisited = new HashSet<CubicCoordinate>();
        // This set keeps track of the biggest cluster of cells
        Set<CubicCoordinate> biggestCluster = new HashSet<CubicCoordinate>();

        // Copy all the cells with the given status into the 'unvisited' Set
        unvisited = getCellsWithStatus(status);

        // Loop trough each cell of the automata's grid
        for (Map.Entry<CubicCoordinate, CellStatus> entry : gridState
                .entrySet()) {

            // If this cell has not already been visited
            if (unvisited.contains(entry.getKey())) {

                // // Here we perform a flood fill // //

                // A temporary set of coordinates, it will contain all the cells
                // of this cluster
                Set<CubicCoordinate> cluster = new HashSet<>();

                // We add the starting cell to the cluster
                cluster.add(entry.getKey());

                // This list will contain lists of cells with a certain distance
                // from the starting cell
                List<List<CubicCoordinate>> fringes = new ArrayList<List<CubicCoordinate>>();

                // We initialize the first element that will contain the cells
                // with distance 0 (the starting point)
                fringes.add(new ArrayList<CubicCoordinate>());

                // We add the starting cell also to the list of cells with
                // distance 0
                fringes.get(0).add(entry.getKey());
                unvisited.remove(entry.getKey());

                int distance = 1;
                // We loop until we filled an entire cluster, if the previous
                // iteration did not found any neighbor with the given status we
                // exit the loop
                while (!(fringes.get(fringes.size() - 1).isEmpty())) {
                    fringes.add(new ArrayList<CubicCoordinate>());

                    // For each coordinate found in the previous iteration we
                    // check if it has neighbors with the given status
                    for (CubicCoordinate coord : fringes.get(distance - 1)) {

                        // We try the 6 possible directions
                        for (int d = 0; d < 6; d++) {

                            // We get the neighbor coordinate in the given
                            // direction
                            CubicCoordinate neighbor = HexCalculator.neighbor(
                                    coord, d);

                            // We check if the neighbor has the given status and
                            // if we did not already visited it
                            if (gridState.get(neighbor) == status
                                    && !cluster.contains(neighbor)) {

                                // Add it to the cluster
                                cluster.add(neighbor);
                                // Add it to the cells with a certain distance
                                // from the starting point
                                fringes.get(distance).add(neighbor);
                                // Remove it from the unvisited cells
                                unvisited.remove(neighbor);

                            }
                        }

                    }

                    // Increase the distance for the next loop
                    distance++;

                }

                // Check if the new cluster we found is bigger than the previous
                if (cluster.size() > biggestCluster.size()) {
                    biggestCluster = cluster;
                }

            }

        }

        // Remove everything but the biggest cluster from the grid state of this
        // automata
        for (Map.Entry<CubicCoordinate, CellStatus> entry : gridState
                .entrySet()) {

            // If the cell of the grid state is not in the biggest cluster then
            // we set it to dead
            if (!biggestCluster.contains(entry.getValue())) {
                entry.setValue(DEAD);
            }

        }

    }

    /**
     * Gets all the cells of the automata's grid with a given status.
     *
     * @param status the status to search for
     * @return the cells with the given status
     */
    private Set<CubicCoordinate> getCellsWithStatus(CellStatus status) {

        Set<CubicCoordinate> cells = new HashSet<CubicCoordinate>();

        for (Map.Entry<CubicCoordinate, CellStatus> entry : gridState
                .entrySet()) {

            if (entry.getValue() == status) {
                cells.add(entry.getKey());
            }

        }

        return cells;

    }

}
