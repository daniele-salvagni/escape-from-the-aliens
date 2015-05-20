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
            .createFromOddQ(13, 5);

    @BeforeClass
    public static void initClass() {

        zoneLoader = new ZoneLoader(ZoneName.DILEMMA);
        zone = zoneLoader.createZone();

    }

    @Test
    public void shouldBeCrated() {

        assertNotNull(zoneLoader);

    }

    @Test
    public void shouldCreateSafeSector() {

        Sector sector = zoneLoader.createSector(
                CubicCoordinate.create(0, 0, 0), SectorType.SAFE);

        assertNotNull(sector);

    }

    @Test
    public void shouldCreateDangerousSector() {

        Sector sector = zoneLoader.createSector(
                CubicCoordinate.create(0, 0, 0), SectorType.DANGEROUS);

        assertNotNull(sector);

    }

    @Test
    public void shouldCreateHatchSector() {

        Sector sector = zoneLoader.createSector(
                CubicCoordinate.create(0, 0, 0), SectorType.HATCH);

        assertNotNull(sector);

    }

    @Test
    public void shouldCreateHumanSector() {

        Sector sector = zoneLoader.createSector(
                CubicCoordinate.create(0, 0, 0), SectorType.HUMAN);

        assertNotNull(sector);

    }

    @Test
    public void shouldCreateAlienSector() {

        Sector sector = zoneLoader.createSector(
                CubicCoordinate.create(0, 0, 0), SectorType.ALIEN);

        assertNotNull(sector);

    }

    @Test
    public void hatchSectorShouldBeCloseable() {

        /* Every hatch should be created of the subclass CloseableSector. */

        Sector sector = zoneLoader.createSector(
                CubicCoordinate.create(0, 0, 0), SectorType.HATCH);

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
