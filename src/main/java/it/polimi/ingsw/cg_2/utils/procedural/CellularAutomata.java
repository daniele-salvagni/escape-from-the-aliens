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
    private static final int DARK_GREEN = 0x003333;
    private static final int LIGHT_GREEN = 0x66CC66;

    private static final int MEDIUM_GREEN = 0x009966;

    /* We use a sort of "Moore Neighborhood" extended to hexagons. */
    private static final List<CubicCoordinate> NEIGHBORHOOD = Arrays.asList(
    /* DIRECTIONS */
    create(1, -1, 0), create(1, 0, -1), create(0, 1, -1), create(-1, 1, 0),
            create(-1, 0, 1), create(0, -1, 1),
            /* DIAGONALS */
            create(2, -1, -1), create(1, 1, -2), create(-1, 2, -1),
            create(-2, 1, 1), create(-1, -1, 2), create(1, -2, 1));

    private static final List<CubicCoordinate> NEIGHBORHOOD2 = Arrays.asList(
    /* DIRECTIONS */
    create(1, -1, 0), create(1, 0, -1), create(0, 1, -1), create(-1, 1, 0),
            create(-1, 0, 1), create(0, -1, 1));

    private int ALIVE_CHANCE = 70;

    private int birthTreshold;
    private int deathTreshold;

    private long seed;
    private int gridWidth;
    private int gridHeight;

    Random rand; // With SEED

    // To initialize as LinkedHashMap (we MUST preserve order)
    private Map<CubicCoordinate, CellStatus> grid;

    /**
     * Instantiates a new cellular automata.
     */
    public CellularAutomata(int birth, int death, int chance) {

        this.birthTreshold = birth;
        this.deathTreshold = death;

        this.seed = 298031;
        this.gridWidth = 23;
        this.gridHeight = 14;

        this.grid = new LinkedHashMap<>();
        this.rand = new Random();

        this.ALIVE_CHANCE = chance;

    }

    public void doThings() {

        generateRectangularGrid();
        createInitialSeed();

        tick();
        tick();
        tick();

        floodFill();

        createSafeSeed();

        safeSectors();
        safeSectors();
        safeSectors();

    }

    protected void createSafeSeed() {

        for (Map.Entry<CubicCoordinate, CellStatus> cell : grid.entrySet()) {

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

        for (Map.Entry<CubicCoordinate, CellStatus> cell : grid.entrySet()) {

            CubicCoordinate cellCoord = cell.getKey();
            CellStatus cellStatus = cell.getValue();

            /* The count is made on the old grid (the previous automaton tick) */
            int alive = countAliveNeighbors(cellCoord);
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

    public int[][] getPixelRepresentation() {

        int[][] pixelMatirx = new int[gridWidth][gridHeight];

        for (Map.Entry<CubicCoordinate, CellStatus> cell : grid.entrySet()) {

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

    protected void generateRectangularGrid() {

        for (int col = 0; col < gridWidth; col++) {
            for (int row = 0; row < gridHeight; row++) {
                CubicCoordinate coord = createFromOddQ(col, row);
                grid.put(coord, DEAD);
            }

        }

    }

    protected void createInitialSeed() {

        for (Map.Entry<CubicCoordinate, CellStatus> cell : grid.entrySet()) {

            if ((Math.abs(gridWidth / 2 - cell.getKey().getOddQCol()) < 4)
                    && (Math.abs(gridHeight / 2 - cell.getKey().getOddQRow()) < 3)) {
                if (rand.nextInt(100) < 85) {
                    cell.setValue(ALIVE);
                }
            } else {
                if (rand.nextInt(100) < ALIVE_CHANCE) {
                    cell.setValue(ALIVE);
                }
            }

            /*
             * if (rand.nextInt(100) < ALIVE_CHANCE) { cell.setValue(ALIVE); }
             */

        }

    }

    /**
     * Executes a tick of the cellular automata
     */
    private void tick() {

        /*
         * We need to work on a copy of the grid, the next state should depend
         * only by the previous one, and not the current one during the
         * iteration.
         */
        Map<CubicCoordinate, CellStatus> newGrid = new LinkedHashMap<>();
        newGrid.putAll(grid);

        for (Map.Entry<CubicCoordinate, CellStatus> cell : newGrid.entrySet()) {

            CubicCoordinate cellCoord = cell.getKey();
            CellStatus cellStatus = cell.getValue();

            /* The count is made on the old grid (the previous automaton tick) */
            int alive = countAliveNeighbors(cellCoord);

            if (cellStatus == DEAD) {
                if ((alive < birthTreshold)) {
                    cell.setValue(ALIVE);
                }
            } else { // cellStatus == ALIVE
                if ((alive < deathTreshold)) {
                    cell.setValue(DEAD);
                }
            }

        }

        grid = newGrid; // Update the grid status

    }

    /**
     * Count the number of alive neighbors of a given cell.
     *
     * @param coord the cell for which to find the neighbors
     * @return the number of alive neighbors
     */
    private int countAliveNeighbors(CubicCoordinate cell) {

        int alive = 0;

        // Loop trough each neighbor and check if it is alive:
        for (CubicCoordinate n : NEIGHBORHOOD) {
            CubicCoordinate neighbor = HexCalculator.add(cell, n);
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

    private int countSafeNeighbors(CubicCoordinate cell) {

        int alive = 0;

        // Loop trough each neighbor and check if it is alive:
        for (CubicCoordinate n : NEIGHBORHOOD) {
            CubicCoordinate neighbor = HexCalculator.add(cell, n);
            if (grid.get(neighbor) == SAFE) {
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

    public void floodFill() {

        // Map<CubicCoordinate, Boolean> alives = new LinkedHashMap<>();
        Set<CubicCoordinate> unvisited = new HashSet<CubicCoordinate>();
        Set<CubicCoordinate> biggest = new HashSet<CubicCoordinate>();

        for (Map.Entry<CubicCoordinate, CellStatus> entry : grid.entrySet()) {

            if (entry.getValue() == ALIVE) {
                // Popolato con colo le celle vive
                // alives.put(entry.getKey(), false); // tenere conto di quelle
                // visitate
                unvisited.add(entry.getKey()); //
            }
        }

        for (Map.Entry<CubicCoordinate, CellStatus> entry : grid.entrySet()) {

            if (unvisited.contains(entry.getKey())) {

                Set<CubicCoordinate> coords = new HashSet<>();
                coords.add(entry.getKey());

                List<List<CubicCoordinate>> fringes = new ArrayList<List<CubicCoordinate>>();
                fringes.add(new ArrayList<CubicCoordinate>());
                fringes.get(0).add(entry.getKey());
                unvisited.remove(entry.getKey());

                for (int i = 1; i <= 999; i++) {
                    fringes.add(new ArrayList<CubicCoordinate>());

                    for (CubicCoordinate c : fringes.get(i - 1)) {

                        for (int j = 0; j < 6; j++) {
                            CubicCoordinate neighbor = HexCalculator.neighbor(
                                    c, j);
                            if (grid.get(neighbor) == ALIVE
                                    && !coords.contains(neighbor)) {
                                coords.add(neighbor);
                                fringes.get(i).add(neighbor);
                                unvisited.remove(neighbor);
                            }
                        }

                    }

                    if (fringes.get(fringes.size() - 1).isEmpty()) {
                        // fringes.remove(fringes.size()-1);
                        break;
                    }

                }

                if (coords.size() > biggest.size()) {
                    biggest = coords;
                }

            }

        }

        Map<CubicCoordinate, CellStatus> newGrid = new LinkedHashMap<>();

        for (CubicCoordinate cell : biggest) {
            newGrid.put(cell, ALIVE);
        }

        grid = newGrid;

    }

}
