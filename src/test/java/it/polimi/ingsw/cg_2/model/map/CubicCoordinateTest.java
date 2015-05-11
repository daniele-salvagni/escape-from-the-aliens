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
    public void shouldBeEqual() {

        assertTrue(CubicCoordinate.create(2, -5, 3).equals(
                CubicCoordinate.create(2, -5, 3)));

    }

    @Test
    public void shouldBeEqualWithConversions() {

        assertTrue(CubicCoordinate.create(2, -5, 3).equals(
                CubicCoordinate.createFromAxial(2, 3)));
        // odd-q in an even column
        assertTrue(CubicCoordinate.create(2, -5, 3).equals(
                CubicCoordinate.createFromOddQ(2, 4)));
        // odd-q in a odd column
        assertTrue(CubicCoordinate.create(1, 1, -2).equals(
                CubicCoordinate.createFromOddQ(1, -2)));

    }


}
