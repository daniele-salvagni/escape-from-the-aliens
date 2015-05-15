package it.polimi.ingsw.cg_2.utils.procedural;

import it.polimi.ingsw.cg_2.model.map.CubicCoordinate;

import java.util.Map;
import java.util.Random;

/**
 * This seed is altered to have a different distribution in the central part of
 * the grid, having an higher concentration of cells helps obtaining a more
 * centered and ordered map.
 *
 */
public class AlteredSeed extends Seed {

    private final int alteredChance;
    private final int alteredWidth;
    private final int alteredHeight;

    /**
     * Instantiates a new altered seed with a different distribution in the
     * central part.
     *
     * @param birthChance the default birth chance
     * @param gridWidth the grid width
     * @param gridHeight the grid height
     * @param alteredChance the altered chance in the central section
     * @param alteredWidth the width of the altered section
     * @param alteredHeight the height of the altered section
     */
    public AlteredSeed(int birthChance, int gridWidth, int gridHeight,
            int alteredChance, int alteredWidth, int alteredHeight) {

        super(birthChance, gridWidth, gridHeight);

        this.alteredChance = alteredChance;
        this.alteredWidth = alteredWidth;
        this.alteredHeight = alteredHeight;

    }

    @Override
    protected void populateGrid(Map<CubicCoordinate, CellStatus> grid,
            long randSeed) {

        Random rand = new Random(randSeed);

        for (Map.Entry<CubicCoordinate, CellStatus> cell : grid.entrySet()) {

            // Distances from the center of the grid
            int horizontalDistance = Math.abs(getGridWidth() / 2
                    - cell.getKey().getOddQCol());
            int verticalDistance = Math.abs(getGridHeight() / 2
                    - cell.getKey().getOddQRow());

            if ((horizontalDistance * 2 < alteredWidth)
                    && (verticalDistance * 2 < alteredHeight)) {

                // Different chance in the middle of the grid
                if (rand.nextInt(100) < alteredChance) {
                    cell.setValue(getAliveStatus());
                }

            } else {

                // Default chance elsewhere
                if (rand.nextInt(100) < getBirthChance()) {
                    cell.setValue(getAliveStatus());
                }

            }

        }

    }

    /**
     * Gets the altered chance for a cell of being alive in the central section
     * of the seed.
     *
     * @return the altered chance
     */
    public int getAlteredChance() {

        return alteredChance;

    }

    /**
     * Gets the width of the altered central section of the seed.
     *
     * @return the altered width
     */
    public int getAlteredWidth() {

        return alteredWidth;

    }

    /**
     * Gets the height of the altered central section of the seed.
     *
     * @return the altered height
     */
    public int getAlteredHeight() {

        return alteredHeight;

    }

}
