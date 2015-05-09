package it.polimi.ingsw.cg_2.model.map;

import java.util.Arrays;
import java.util.List;

/**
 * This class models the constraints of an <b>hexagonal map</b>, the algorithms
 * are implemented using {@link CubicCoordinate}, it does contain static methods
 * to work with coordinates (arithmetical operations, distance calculations,
 * neighbors, ...). Some algorithm in this class are a Java implementation of
 * the work done by <i>Amit Patel</i>, for more informations see <a
 * href="http://www.redblobgames.com/grids/hexagons/">his website</a>.
 */
public class HexCalculator {

    /**
     * The six possible directions in a CubicCoordinate, starting from SE and
     * continuing in a counterclockwise order. By adding them to an existing
     * coordinate it is possible to get its neighbors. We use 3 coordinates for
     * clarity.
     */
    private static final List<CubicCoordinate> DIRECTIONS = Arrays.asList(
    /*
     * Moving one space in hex coordinates involves changing one of the 3 cube
     * coordinates by +1 and changing another one by -1 (the sum must remain 0).
     */
    CubicCoordinate.create(1, -1, 0), CubicCoordinate.create(1, 0, -1),
            CubicCoordinate.create(0, 1, -1), CubicCoordinate.create(-1, 1, 0),
            CubicCoordinate.create(-1, 0, 1), CubicCoordinate.create(0, -1, 1));
    
    /**
     * Suppress the default constructor for noninstantiability (Effective Java -
     * Item 4).
     */
    private HexCalculator() {
        throw new AssertionError();
    }

    /**
     * Returns a new CubicCoordinate obtained by the sum of 2.
     *
     * @param coord1 the first coordinate
     * @param coord2 the coordinate to add
     * @return a new CubicCoordinate
     */
    public static CubicCoordinate add(CubicCoordinate coord1,
            CubicCoordinate coord2) {

        return CubicCoordinate.createFromAxial(coord1.getX() + coord2.getX(),
                coord1.getZ() + coord2.getZ());

    }

    /**
     * Returns a new CubicCoordinate obtained by subtracting another
     * CubicCoordinate to this one.
     *
     * @param coord1 the first coordinate
     * @param coord2 the coordinate to subtract
     * @return a new CubicCoordinate
     */
    public static CubicCoordinate sub(CubicCoordinate coord1,
            CubicCoordinate coord2) {

        return CubicCoordinate.createFromAxial(coord1.getX() - coord2.getX(),
                coord1.getZ() - coord2.getZ());

    }

    /**
     * Gets a CubicCoordinate representing one of the <b>six</b> possible
     * directions, starting from 0 for the SE direction and continuing in a
     * counterclockwise order.
     *
     * @param direction a number from 0 to 5 representing the direction
     * @return a CubicCoordinate representing the direction
     */
    public static CubicCoordinate getDirection(int direction) {

        if (direction < 0 || direction > 5) {
            throw new IllegalArgumentException(
                    "Argument direction should be between 0 and 5.");
        }

        return DIRECTIONS.get(direction);

    }

    /**
     * Returns the neighbor coordinate of a given CubicCoordinate in a given
     * direction.
     *
     * @param coord the for which to find the neighbor
     * @param direction a number from 0 to 5 representing the direction
     * @return a CubicCoordinate representing the direction
     */
    public static CubicCoordinate neighbor(CubicCoordinate coord, int direction) {

        return add(coord, getDirection(direction));

    }

    /**
     * Returns the distance between two CubicCoordinate (no obstacles).
     *
     * @param coord1 the first coordinate
     * @param coord2 the second coordinate
     * @return the distance between the two coordinates
     */
    public static int distance(CubicCoordinate coord1, CubicCoordinate coord2) {

        /*
         * In the cube coordinate system, each hexagon is a cube in 3d space.
         * Adjacent hexagons are distance 1 apart in the hex grid but distance 2
         * apart in the cube grid. This makes distances simple. In a square
         * grid, Manhattan distances are abs(dx) + abs(dy). In a cube grid,
         * Manhattan distances are abs(dx) + abs(dy) + abs(dz). The distance on
         * a hex grid is half that.
         */
        int distance = (Math.abs(coord1.getX() - coord2.getX())
                + Math.abs(coord1.getY() - coord2.getY()) + Math.abs(coord1
                .getZ() - coord2.getZ())) / 2;

        return distance;

    }

}
