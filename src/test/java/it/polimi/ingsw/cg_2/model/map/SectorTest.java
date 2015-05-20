package it.polimi.ingsw.cg_2.model.map;

import it.polimi.ingsw.cg_2.model.map.Sector.SectorType;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class SectorTest {

    private static CubicCoordinate coordinate;
    private static SectorType sectorType;
    private static Sector sector;

    @BeforeClass
    public static void initClass() {

        coordinate = CubicCoordinate.create(0, 0, 0);
        sectorType = SectorType.SAFE;
        sector = new Sector(coordinate, sectorType);

    }

    @Test
    public void shouldBeCreated() throws Exception {

        assertNotNull(sector);

    }

    @Test
    public void shouldGetType() {

        assertEquals(sectorType, sector.getType());

    }

    @Test
    public void shouldGetCoordinate() {

        assertEquals(coordinate, sector.getCooridnate());

    }

    @Test
    public void testToString() {

        // We want toString to be overridden
        assertFalse(sector.toString().contains("@"));

    }

}
