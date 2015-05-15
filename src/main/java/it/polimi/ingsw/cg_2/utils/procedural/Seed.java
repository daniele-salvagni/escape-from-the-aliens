package it.polimi.ingsw.cg_2.utils.procedural;

import static it.polimi.ingsw.cg_2.model.map.CubicCoordinate.createFromOddQ;
import it.polimi.ingsw.cg_2.model.map.CubicCoordinate;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * This class generates evenly distributed Seeds to be used in a
 * {@link CellularAutomata}.
 **/
public class Seed {

    /* The chance of a cell to be born. */
    private final int birthChance;

    /* The rectangular size of the grid. */
    private final int gridWidth;
    private final int gridHeight;

    /* The statuses to be used as alive and dead. */
    private CellStatus aliveStatus;
    private CellStatus deadStatus;

    /**
     * Instantiates a new seed generator.
     *
     * @param birthChance the chance of a cell of being alive
     * @param gridWidth the width of the rectangular grid
     * @param gridHeight the height of the rectangular grid
     */
    public Seed(int birthChance, int gridWidth, int gridHeight) {

        this.birthChance = birthChance;
        this.gridWidth = gridWidth; // Standard is 23
        this.gridHeight = gridHeight; // Standard is 14

        aliveStatus = CellStatus.ALIVE;
        deadStatus = CellStatus.DEAD;

    }

    /**
     * Returns a pseudo random seed to be used in a {@link CellularAutomata}, it
     * is generated from the parameters of this Seed generator and the provided
     * seed used by the internal pseudo random number generator.
     *
     * @param randSeed the seed to be used for the internal pseudo random number
     *            generator
     * @return a {@link Map} containing a rectangular grid and a status for
     *         every cell
     * 
     * @see Random
     */
    public Map<CubicCoordinate, CellStatus> nextGrid(long randSeed) {

        Map<CubicCoordinate, CellStatus> seedGrid;
        seedGrid = generateRectangularDeadGrid();

        populateGrid(seedGrid, randSeed);

        return seedGrid;

    }

    /**
     * Pseudo-randomly changes the status of a given grid to the aliveStatus.
     *
     * @param grid the grid to be populated
     * @param randSeed the seed used for the pseudo random number generator
     * 
     * @see Random
     */
    protected void populateGrid(Map<CubicCoordinate, CellStatus> grid,
            long randSeed) {

        Random rand = new Random(randSeed);

        for (Map.Entry<CubicCoordinate, CellStatus> cell : grid.entrySet()) {

            if (rand.nextInt(100) < getBirthChance()) {
                cell.setValue(getDeadStatus());
            }

        }

    }

    /**
     * Generates a grid of dead cells with a rectangular shape.
     *
     * @return a map containing all the cells and their status
     */
    protected Map<CubicCoordinate, CellStatus> generateRectangularDeadGrid() {

        Map<CubicCoordinate, CellStatus> rectGrid = new LinkedHashMap<>();

        for (int col = 0; col < getGridWidth(); col++) {
            for (int row = 0; row < getGridHeight(); row++) {
                // It is easier with OddQ coordinates
                CubicCoordinate coord = createFromOddQ(col, row);
                rectGrid.put(coord, getDeadStatus());
            }

        }

        return rectGrid;

    }

    /**
     * Gets the grid width.
     *
     * @return the grid width
     */
    public int getGridWidth() {

        return gridWidth;

    }

    /**
     * Gets the grid height.
     *
     * @return the grid height
     */
    public int getGridHeight() {

        return gridHeight;

    }

    /**
     * Gets the birth chance.
     *
     * @return the birth chance
     */
    protected int getBirthChance() {

        return birthChance;

    }

    /**
     * Gets the status to be used as the deadStatus in this Seed generator
     * (default is DEAD).
     *
     * @return the status used as the deadStatus
     */
    public CellStatus getDeadStatus() {

        return deadStatus;
    }

    /**
     * Changes the status to be used as the deadStatus in this Seed generator
     * (default is DEAD).
     *
     * @param aliveStatus the status to be used as the deadStatus
     */
    public void setDeadStatus(CellStatus deadStatus) {

        this.deadStatus = deadStatus;

    }

    /**
     * Gets the status to be used as the aliveStatus in this Seed generator
     * (default is ALIVE).
     *
     * @return the status used as the aliveStatus
     */
    public CellStatus getAliveStatus() {

        return aliveStatus;

    }

    /**
     * Changes the status to be used as the aliveStatus in this Seed generator
     * (default is ALIVE).
     *
     * @param aliveStatus the status to be used as the aliveStatus
     */
    public void setAliveStatus(CellStatus aliveStatus) {

        this.aliveStatus = aliveStatus;

    }

}
