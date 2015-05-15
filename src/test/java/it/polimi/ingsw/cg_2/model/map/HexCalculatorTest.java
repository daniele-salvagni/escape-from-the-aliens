package it.polimi.ingsw.cg_2.model.map;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class HexCalculatorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldAddCoordinates() {

        CubicCoordinate expected = CubicCoordinate.create(5, -8, 3);

        CubicCoordinate coord1 = CubicCoordinate.create(2, -3, 1);
        CubicCoordinate coord2 = CubicCoordinate.create(3, -5, 2);

        CubicCoordinate sum = HexCalculator.add(coord1, coord2);

        assertEquals(expected, sum);

    }

    @Test
    public void shouldSubCoordinates() {

        CubicCoordinate expected = CubicCoordinate.create(-1, 2, -1);

        CubicCoordinate coord1 = CubicCoordinate.create(2, -3, 1);
        CubicCoordinate coord2 = CubicCoordinate.create(3, -5, 2);

        CubicCoordinate sum = HexCalculator.sub(coord1, coord2);

        assertEquals(expected, sum);

    }

    @Test
    public void shouldGetDirections() {

        List<CubicCoordinate> directions = new ArrayList<>();

        directions.add(CubicCoordinate.create(1, -1, 0));
        directions.add(CubicCoordinate.create(1, 0, -1));
        directions.add(CubicCoordinate.create(0, 1, -1));
        directions.add(CubicCoordinate.create(-1, 1, 0));
        directions.add(CubicCoordinate.create(-1, 0, 1));
        directions.add(CubicCoordinate.create(0, -1, 1));

        for (int i = 0; i < 6; i++) {
            CubicCoordinate direction = HexCalculator.getDirection(i);
            // Remove returns true if the element exists
            assertTrue(directions.remove(direction));
        }

        assertTrue(directions.isEmpty());

    }

    @Test
    public void shouldGetNeighbors() {

        CubicCoordinate coord = CubicCoordinate.create(1, -3, 2);

        List<CubicCoordinate> neighbors = new ArrayList<>();

        neighbors.add(CubicCoordinate.create(2, -4, 2));
        neighbors.add(CubicCoordinate.create(2, -3, 1));
        neighbors.add(CubicCoordinate.create(1, -2, 1));
        neighbors.add(CubicCoordinate.create(0, -2, 2));
        neighbors.add(CubicCoordinate.create(0, -3, 3));
        neighbors.add(CubicCoordinate.create(1, -4, 3));

        for (int i = 0; i < 6; i++) {
            CubicCoordinate neighbor = HexCalculator.neighbor(coord, i);
            // Remove returns true if the element exists
            assertTrue(neighbors.remove(neighbor));
        }

        assertTrue(neighbors.isEmpty());

    }

    @Test
    public void shouldGetDistanceAsTheCrowFlies() {

        int expected = 8;

        CubicCoordinate coord1 = CubicCoordinate.create(-2, 4, -2);
        CubicCoordinate coord2 = CubicCoordinate.create(2, -4, 2);

        assertEquals(expected,
                HexCalculator.distanceAsTheCrowFlies(coord1, coord2));

    }

    @Test
    public void shouldGetDistanceAsTheCrowFliesWithSamePosition() {

        int expected = 0;

        CubicCoordinate coord1 = CubicCoordinate.create(1, -2, 1);
        CubicCoordinate coord2 = CubicCoordinate.create(1, -2, 1);

        assertEquals(expected,
                HexCalculator.distanceAsTheCrowFlies(coord1, coord2));

    }

    @Test
    public void shouldGetReachableCoordinatesWithObstacles() {

        // A section of the FERMI map

        Set<CubicCoordinate> grid = new HashSet<>();

        CubicCoordinate coord1 = CubicCoordinate.createFromOddQ(8, 6);
        CubicCoordinate coord2 = CubicCoordinate.createFromOddQ(8, 7);

        CubicCoordinate coord3 = CubicCoordinate.createFromOddQ(9, 4);
        CubicCoordinate coord4 = CubicCoordinate.createFromOddQ(9, 5);
        CubicCoordinate coord5 = CubicCoordinate.createFromOddQ(9, 7);

        CubicCoordinate coord6 = CubicCoordinate.createFromOddQ(10, 4);
        CubicCoordinate coord7 = CubicCoordinate.createFromOddQ(10, 8);

        CubicCoordinate coord8 = CubicCoordinate.createFromOddQ(11, 4);
        CubicCoordinate coord9 = CubicCoordinate.createFromOddQ(11, 5);
        CubicCoordinate coord10 = CubicCoordinate.createFromOddQ(11, 6);
        CubicCoordinate coord11 = CubicCoordinate.createFromOddQ(11, 7);
        CubicCoordinate coord12 = CubicCoordinate.createFromOddQ(11, 8);

        grid.add(coord1);
        grid.add(coord2);
        grid.add(coord3);
        grid.add(coord4);
        grid.add(coord5);
        grid.add(coord6);
        grid.add(coord7);
        grid.add(coord8);
        grid.add(coord9);
        grid.add(coord10);
        grid.add(coord11);
        grid.add(coord12);

        Set<CubicCoordinate> range = HexCalculator.reachableCoordinates(grid,
                coord7, 3);

        assertEquals(8, range.size());
        assertTrue(range.contains(coord1));
        assertTrue(range.contains(coord2));
        assertTrue(range.contains(coord5));
        assertTrue(range.contains(coord7)); // Obviously itself
        assertTrue(range.contains(coord9));
        assertTrue(range.contains(coord10));
        assertTrue(range.contains(coord11));
        assertTrue(range.contains(coord12));

    }

    @Test
    public void shouldThrowExceptionIfDirectionIsMoreThanFive() {

        thrown.expect(IllegalArgumentException.class);

        HexCalculator.getDirection(-1);

    }

    @Test
    public void shouldThrowExceptionIfDirectionIsLessThanZero() {

        thrown.expect(IllegalArgumentException.class);

        HexCalculator.getDirection(6);

    }

}
