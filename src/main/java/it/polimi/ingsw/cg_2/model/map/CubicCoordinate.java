package it.polimi.ingsw.cg_2.model.map;

/**
 * This class represent a Cubic Coordinate. This coordinate system is specific
 * for hexagonal grids and so it should not be extended or reused in other
 * coordinate and/or grid systems. Alongside the class HexagonalCalculator
 * provides a complete model and the constraints for an hexagonal grid.
 * <p>
 * An hexagonal grid can be imagined as a cubic grid sliced out with a diagonal
 * plane at x+y+z=0, so only two coordinates are needed. The two-parameter
 * constructor takes by convention the x and z coordinate as they could be used
 * in a trapezoidal (or axial) coordinate system.
 * <p>
 * By using this representation the same algorithms used for cubic grids can be
 * used in 2D hexagonal grids.
 * <p>
 * Static factory methods are provided to avoid confusion between the two
 * representations (Effective Java - Item 1).
 * <p>
 * This class must remain immutable, it is also used as an {@link HashMap} key
 * inside a {@link Zone}.
 * */
public final class CubicCoordinate {

    /*
     * The z-axis coordinate is not needed as z = x + y so, by convention, we
     * internally store the correspondent axial coordinate where q = x and r = z
     */
    private final int x;
    private final int z;

    /**
     * Instantiates a new cubic coordinate, constructor is private as we use
     * static factories so it is possible to have multiple constructors without
     * causing confusion with parameters.
     *
     * @param x the x coordinate of a CubicCoordinate
     * @param z the z coordinate of a CubicCoordinate
     */
    private CubicCoordinate(int x, int z) {

        this.x = x;
        this.z = z;

    }

    /**
     * Creates a CubicCoordinate object, one of the three could be implicit,
     * this static factory method is provided only for code readability and
     * validation. x + y + z must equal zero, otherwise an
     * IllegalArgumentException is thrown.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     * @return a new CubicCoordinate object
     */
    public static CubicCoordinate create(int x, int y, int z) {

        if ((x + y + z) == 0) {
            return new CubicCoordinate(x, z);
        } else {
            throw new IllegalArgumentException("x + y + z must equal 0.");
        }

    }

    /**
     * This static factory method creates a CubicCoordinate object from an axial
     * coordinate system, by convention the parameters are:
     *
     * @param q the x coordinate of a CubicCoordinate (q for axial)
     * @param r the z coordinate of a CubicCoordinate (r for axial)
     * @return a new CubicCoordinate object
     */
    public static CubicCoordinate createFromAxial(int q, int r) {

        return new CubicCoordinate(q, r);

    }

    /**
     * This static factory method created a CubicCoordinate object form an
     * offset "odd-q" coordinate system.
     *
     * @param col the column in the odd-q coordinate system
     * @param row the row in the odd-q coordinate system
     * @return a new CubicCooridnate object
     */
    public static CubicCoordinate createFromOddQ(int col, int row) {

        return new CubicCoordinate(col, row - (col - (col & 1)) / 2);

    }

    /**
     * Gets the x-axis coordinate of a CubicCoordinate (q in axial system).
     *
     * @return the x coordinate
     */
    public int getX() {

        return x;

    }

    /**
     * Gets the y-axis coordinate of a CubicCoordinate.
     *
     * @return the y coordinate
     */
    public int getY() {

        return -(x + z);

    }

    /**
     * Gets the z-axis coordinate of a CubicCoordinate (r in axial system).
     *
     * @return the z coordinate
     */
    public int getZ() {

        return z;

    }

    /**
     * Returns the column of the odd-q offset representation of this coordinate.
     *
     * @return the odd-q col
     */
    public int getOddQCol() {

        return x;

    }

    /**
     * Returns the row of the odd-q offset representation of this coordinate.
     *
     * @return the odd-q row
     */
    public int getOddQRow() {

        return z + (x - (x & 1)) / 2;

    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + z;
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CubicCoordinate other = (CubicCoordinate) obj;
        if (x != other.x)
            return false;
        if (z != other.z)
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     * 
     * Example: CubicCoordinate [x=-6, y=3, z=3]
     */
    @Override
    public String toString() {

        return "CubicCoordinate [x=" + x + ", y=" + (-x - z) + ", z=" + z
                + " | col=" + getOddQCol() + ", row=" + getOddQRow() + "]";
    }

}
