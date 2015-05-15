package it.polimi.ingsw.cg_2.utils.procedural;

import static it.polimi.ingsw.cg_2.utils.procedural.CellStatus.*;
import static it.polimi.ingsw.cg_2.model.map.CubicCoordinate.*;
import it.polimi.ingsw.cg_2.model.map.CubicCoordinate;
import it.polimi.ingsw.cg_2.model.map.HexCalculator;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A cellular automata consists of a grid of cells 'G' where each cell 'c' can
 * be in a finite number of states. For each cell, a set of cells called its
 * neighborhood is defined relative to the specified cell. An initial state (t =
 * 0) is selected by assigning a state for each cell, this is generally called
 * the seed of the automaton. A new generation is created (advancing t by 1),
 * according to a fixed rule that determines the new state of each cell in terms
 * of the current state of the cell and the states of the cells in its
 * neighborhood, a single generation is usually called a tick.
 * <p>
 * In this automata we use two statuses: ALIVE and DEAD. A seed grid must be
 * provided to start the automata, it is possible to set the birth and death
 * threshold and to apply a certain number of ticks to the automata. This
 * implementation is specific for hexagonal grids and used a sort of Moore
 * neighborhood (12 neighbors per cell).
 */
public class CellularAutomata {

    /**
     * We use a sort of "Moore Neighborhood" extended to hexagons, by using
     * directions and diagonals we have a total of 12 neighbors.
     */
    private static final List<CubicCoordinate> NEIGHBORHOOD = Arrays.asList(
            create(1, -1, 0), create(1, 0, -1), create(0, 1, -1),
            create(-1, 1, 0), create(-1, 0, 1), create(0, -1, 1),
            /* DIAGONALS */
            create(2, -1, -1), create(1, 1, -2), create(-1, 2, -1),
            create(-2, 1, 1), create(-1, -1, 2), create(1, -2, 1));

    // Tick configuration
    private int birthThreshold;
    private int deathThreshold;

    private final Map<CubicCoordinate, CellStatus> gridState;

    /**
     * Instantiates a new cellular automata with given birth and death threshold
     * and an initial seed.
     *
     * @param birthThreshold the birth threshold of a cell
     * @param deathThreshold the death threshold of a cell
     * @param seed the seed grid of the automata
     */
    public CellularAutomata(int birthThreshold, int deathThreshold,
            Map<CubicCoordinate, CellStatus> seed) {

        // Set automata rules
        this.birthThreshold = birthThreshold;
        this.deathThreshold = deathThreshold;

        // We want to be sure that we are working with a LinkedHashMap
        gridState = seed;

    }

    /**
     * Apply a certain amount of ticks to the automata.
     *
     * @param number the number of ticks to perform
     */
    public void applyTicks(int number) {

        for (int n = 0; n < number; n++) {
            applyTick();
        }

        GridProcessor.replaceSmallerClusters(gridState, ALIVE, DEAD);

    }

    /**
     * Gets the grid of the automata in its current state. A copy of the Map is
     * returned to reduce mutability.
     *
     * @return a copy of the Map containing the grid of the automata in its
     *         current status
     */
    public Map<CubicCoordinate, CellStatus> getGrid() {

        // We return a copy to keep the original immutable
        return new LinkedHashMap<>(gridState);

    }

    /**
     * Executes a single tick of the cellular automata, it is applied to the
     * whole grid simultaneously.
     */
    private void applyTick() {

        /*
         * We need to keep a copy of the grid to apply the rules to each cell
         * 'simultaneously'. The next state should depend only by the previous
         * one, and not the current one during the iteration.
         */
        Map<CubicCoordinate, CellStatus> oldState = new LinkedHashMap<>();
        oldState.putAll(gridState);

        // We loop trough the gridState
        for (Map.Entry<CubicCoordinate, CellStatus> cell : gridState.entrySet()) {

            CubicCoordinate cellCoord = cell.getKey();
            CellStatus cellStatus = cell.getValue();

            /* The count is made on the oldState (the previous automaton tick) */
            int alive = countNeighborsWithStatus(oldState, cellCoord, ALIVE);

            if (cellStatus == DEAD) {
                if (alive < birthThreshold) {
                    cell.setValue(ALIVE);
                }
            } else if (cellStatus == ALIVE) {
                if (alive < deathThreshold) {
                    cell.setValue(DEAD);
                }
            }

        }

    }

    /**
     * Count the number of neighbors of a given cell in a certain grid with a
     * given status.
     *
     * @param grid the grid on which to do the count
     * @param cell the cell of the grid
     * @param status the status to search for
     * @return the number of alive neighbors
     */
    private int countNeighborsWithStatus(Map<CubicCoordinate, CellStatus> grid,
            CubicCoordinate cell, CellStatus status) {

        int count = 0;

        // Loop trough each neighbor and check if it is alive:
        for (CubicCoordinate shift : NEIGHBORHOOD) {
            CubicCoordinate neighbor = HexCalculator.add(cell, shift);
            if (grid.get(neighbor) == status) {
                /*
                 * If the coordinate is outside the grid map.get(..) returns
                 * null, so it will work as intended. We consider not existing
                 * cells (outside the rectangular grid) as dead.
                 */
                count++;
            } else if (grid.get(neighbor) == null) {
                // alive += 3;
                // TODO: Should do some tests
            }
        }

        return count;

    }

}
