package it.polimi.ingsw.cg_2.model.map;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CubicCoordinateTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldBeCreated() {

        // x + y + z = 0.
        assertNotNull(CubicCoordinate.create(2, -5, 3));

    }

    @Test
    public void shouldBeCreatedZero() {

        assertNotNull(CubicCoordinate.create(0, 0, 0));

    }

    @Test
    public void shouldFailIfNotValid() {

        thrown.expect(IllegalArgumentException.class);
        // x + y + z != 0.
        CubicCoordinate.create(2, -5, 4);

    }

    @Test
    public void shouldBeCreatedFromAxial() {

        assertNotNull(CubicCoordinate.createFromAxial(3, -5));

    }

    @Test
    public void shouldBeConvertedCorrectly() {

        assertTrue(CubicCoordinate.create(2, -5, 3).equals(
                CubicCoordinate.createFromAxial(2, 3)));
        // odd-q in an even column
        assertTrue(CubicCoordinate.create(2, -5, 3).equals(
                CubicCoordinate.createFromOddQ(2, 4)));
        // odd-q in a odd column
        assertTrue(CubicCoordinate.create(1, 1, -2).equals(
                CubicCoordinate.createFromOddQ(1, -2)));

    }

    @Test
    public void shouldGetX() {

        int x = 2;
        CubicCoordinate coordinate = CubicCoordinate.create(x, -5, 3);
        assertEquals(coordinate.getX(), x);

    }

    @Test
    public void shouldGetY() {

        int y = -5;
        CubicCoordinate coordinate = CubicCoordinate.create(2, y, 3);
        assertEquals(coordinate.getY(), y);

    }

    @Test
    public void shouldGetZ() {

        int z = 3;
        CubicCoordinate coordinate = CubicCoordinate.create(2, -5, z);
        assertEquals(coordinate.getZ(), z);

    }

    @Test
    public void shouldGetOddQColumn() {

        int col = -5;
        CubicCoordinate coordinate = CubicCoordinate.createFromOddQ(col, 3);
        assertEquals(col, coordinate.getOddQCol());

    }

    @Test
    public void shouldGetOddQRow() {

        int row = 3;
        CubicCoordinate coordinate = CubicCoordinate.createFromOddQ(-5, row);
        assertEquals(row, coordinate.getOddQRow());

    }

    @Test
    public void testEquals() {

        CubicCoordinate coord1 = CubicCoordinate.createFromAxial(2, 3);
        CubicCoordinate coord2 = CubicCoordinate.createFromAxial(2, 3);

        CubicCoordinate coord3 = CubicCoordinate.createFromAxial(1, 5);

        assertTrue(coord1.equals(coord1));
        assertTrue(coord1.equals(coord2));
        assertTrue(coord2.equals(coord1));
        assertFalse(coord1.equals(coord3));
        assertFalse(coord3.equals(coord1));
        assertFalse(coord1.equals(new Integer(5)));
        assertFalse(coord1.equals(null));

    }
}
