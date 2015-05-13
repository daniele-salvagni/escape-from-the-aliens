package it.polimi.ingsw.cg_2.model.map;

import it.polimi.ingsw.cg_2.model.map.CloseableSector.SectorStatus;
import it.polimi.ingsw.cg_2.model.map.Sector.SectorType;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CloseableSectorTest {

    private CubicCoordinate coordinate;
    private SectorType sectorType;
    private CloseableSector sector;

    @Before
    public void initClass() {

        coordinate = CubicCoordinate.create(0, 0, 0);
        sectorType = SectorType.HATCH;
        sector = new CloseableSector(coordinate, sectorType);

    }

    @Test
    public void shouldGetStatus() {

        assertEquals(SectorStatus.OPEN, sector.getStatus());

    }

    @Test
    public void shouldClose() {

        sector.close();
        assertEquals(SectorStatus.CLOSED, sector.getStatus());

    }

    @Test
    public void shouldOpen() {

        sector.close();
        sector.open();
        assertEquals(SectorStatus.OPEN, sector.getStatus());

    }

}
