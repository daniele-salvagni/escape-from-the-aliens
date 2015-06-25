package it.polimi.ingsw.cg_2.model.map;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_2.model.map.Sector.SectorType;

import org.junit.BeforeClass;
import org.junit.Test;

public class ZoneLoaderTest {

    private static ZoneLoader zoneLoader;
    private static Zone zone;

    private static final int ZONE_SECTORS = 16;
    private static final CubicCoordinate ALIEN_SECTOR = CubicCoordinate
            .createFromOddQ(14, 6);

    private static CubicCoordinate genericCoord;

    @BeforeClass
    public static void initClass() {

        zoneLoader = new ZoneLoader(ZoneName.DILEMMA);
        zone = zoneLoader.createZone();

        genericCoord = CubicCoordinate.create(1, -3, 2);

    }

    @Test
    public void shouldBeCrated() {

        assertNotNull(zoneLoader);

    }

    @Test
    public void shouldCreateSafeSector() {

        SectorType type = SectorType.SAFE;

        Sector sector = zoneLoader.createSector(genericCoord, type);

        assertNotNull(sector);
        assertEquals(type, sector.getType());
        assertEquals(genericCoord, sector.getCooridnate());

    }

    @Test
    public void shouldCreateDangerousSector() {

        SectorType type = SectorType.DANGEROUS;

        Sector sector = zoneLoader.createSector(genericCoord, type);

        assertNotNull(sector);
        assertEquals(type, sector.getType());
        assertEquals(genericCoord, sector.getCooridnate());

    }

    @Test
    public void shouldCreateHatchSector() {

        SectorType type = SectorType.HATCH;

        Sector sector = zoneLoader.createSector(genericCoord, type);

        assertNotNull(sector);
        assertEquals(type, sector.getType());
        assertEquals(genericCoord, sector.getCooridnate());

    }

    @Test
    public void shouldCreateHumanSector() {

        SectorType type = SectorType.HUMAN;

        Sector sector = zoneLoader.createSector(genericCoord, type);

        assertNotNull(sector);
        assertEquals(type, sector.getType());
        assertEquals(genericCoord, sector.getCooridnate());

    }

    @Test
    public void shouldCreateAlienSector() {

        SectorType type = SectorType.ALIEN;

        Sector sector = zoneLoader.createSector(genericCoord, type);

        assertNotNull(sector);
        assertEquals(type, sector.getType());
        assertEquals(genericCoord, sector.getCooridnate());

    }

    @Test
    public void hatchSectorShouldBeCloseable() {

        /* Every hatch should be created of the subclass CloseableSector. */

        SectorType type = SectorType.HATCH;

        Sector sector = zoneLoader.createSector(genericCoord, type);

        assertTrue(sector instanceof CloseableSector);

    }

    @Test
    public void shouldLoadAValidZone() {

        assertNotNull(zone);

    }

    @Test
    public void shouldCreateAllTheNonemptySectors() {

        // The number of sectors of the Zone DILEMMA
        assertTrue(zone.getCoordinates().size() == ZONE_SECTORS);

    }

    @Test
    public void shouldCreateSectorsInTheCorrectPosition() {

        assertEquals(zone.getAlienSector().getCooridnate(), ALIEN_SECTOR);

    }

}
