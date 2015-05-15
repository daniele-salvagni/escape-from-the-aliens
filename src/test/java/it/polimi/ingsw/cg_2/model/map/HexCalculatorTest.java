package it.polimi.ingsw.cg_2.model.map;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class HexCalculatorTest {

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

}
