package it.polimi.ingsw.cg_2.model.map;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import it.polimi.ingsw.cg_2.model.map.Sector.SectorType;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ZoneTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    // // CONSTRUCTOR // //

    @Test
    public void shouldBeCreated() {

        Set<Sector> sectors = new HashSet<Sector>();
        sectors.add(new Sector(CubicCoordinate.createFromAxial(1, 2),
                SectorType.ALIEN));

        assertNotNull(new Zone(sectors));

    }

    // // GETTERS // //

    @Test
    public void shouldGetMapOfSectors() {

        Set<Sector> sectors = new HashSet<Sector>();

        Sector sector1 = new Sector(CubicCoordinate.createFromAxial(1, 2),
                SectorType.ALIEN);
        Sector sector2 = new Sector(CubicCoordinate.createFromAxial(1, 3),
                SectorType.HUMAN);

        sectors.add(sector1);
        sectors.add(sector2);

        Zone zone = new Zone(sectors);

        Map<CubicCoordinate, Sector> sectorMap = zone.getSectorsMap();

        // Should not be null
        assertNotNull(sectorMap);
        // Key-Value pairs should be correct
        assertEquals(sector1,
                sectorMap.get(CubicCoordinate.createFromAxial(1, 2)));
        assertEquals(sector2,
                sectorMap.get(CubicCoordinate.createFromAxial(1, 3)));
        // Should contain the correct number of items
        assertEquals(2, sectorMap.size());

    }

    @Test
    public void shouldGetSectorFromCoordinate() {

        Set<Sector> sectors = new HashSet<Sector>();

        Sector sector1 = new Sector(CubicCoordinate.createFromAxial(1, 2),
                SectorType.ALIEN);
        Sector sector2 = new Sector(CubicCoordinate.createFromAxial(1, 3),
                SectorType.HUMAN);

        sectors.add(sector1);
        sectors.add(sector2);

        Zone zone = new Zone(sectors);

        assertEquals(sector1,
                zone.getSector(CubicCoordinate.createFromAxial(1, 2)));
        assertEquals(sector2,
                zone.getSector(CubicCoordinate.createFromAxial(1, 3)));

    }

    @Test
    public void shouldGetZoneCoordinates() {

        Set<Sector> sectors = new HashSet<Sector>();

        CubicCoordinate coord1 = CubicCoordinate.createFromAxial(1, 2);
        CubicCoordinate coord2 = CubicCoordinate.createFromAxial(1, 3);

        Sector sector1 = new Sector(coord1, SectorType.ALIEN);
        Sector sector2 = new Sector(coord2, SectorType.HUMAN);

        sectors.add(sector1);
        sectors.add(sector2);

        Zone zone = new Zone(sectors);

        Set<CubicCoordinate> coordinates = zone.getCoordinates();

        assertTrue(coordinates.contains(coord1));
        assertTrue(coordinates.contains(coord2));
        assertEquals(2, coordinates.size());

    }

    // // EXCEPTIONS // //

    @Test
    public void shouldThrowExceptionIfTwoSectorsHaveTheSameCoordinate() {

        Set<Sector> sectors = new HashSet<Sector>();

        // We add two sectors with the same coordinate
        Sector sector1 = new Sector(CubicCoordinate.createFromAxial(1, 2),
                SectorType.ALIEN);
        Sector sector2 = new Sector(CubicCoordinate.createFromAxial(1, 2),
                SectorType.HUMAN);

        sectors.add(sector1);
        sectors.add(sector2);

        thrown.expect(IllegalArgumentException.class);

        new Zone(sectors);

    }

    @Test
    public void shouldThrowExceptionIfWrongNumberOfAlienSectors() {

        Set<Sector> sectors = new HashSet<Sector>();

        // We add two alien sectors
        Sector sector1 = new Sector(CubicCoordinate.createFromAxial(1, 2),
                SectorType.ALIEN);
        Sector sector2 = new Sector(CubicCoordinate.createFromAxial(1, 3),
                SectorType.ALIEN);

        sectors.add(sector1);
        sectors.add(sector2);

        Zone zone = new Zone(sectors);

        thrown.expect(IllegalStateException.class);

        zone.getAlienSector();

    }

    @Test
    public void shouldThrowExceptionIfWrongNumberOfHumanSectors() {

        Set<Sector> sectors = new HashSet<Sector>();

        // We add two human sectors
        Sector sector1 = new Sector(CubicCoordinate.createFromAxial(1, 2),
                SectorType.HUMAN);
        Sector sector2 = new Sector(CubicCoordinate.createFromAxial(1, 3),
                SectorType.HUMAN);

        sectors.add(sector1);
        sectors.add(sector2);

        Zone zone = new Zone(sectors);

        thrown.expect(IllegalStateException.class);

        zone.getHumanSector();

    }

    @Test
    public void shouldThrowExceptionIfNoHatches() {

        Set<Sector> sectors = new HashSet<Sector>();

        // We don't add hatches
        Sector sector1 = new Sector(CubicCoordinate.createFromAxial(1, 2),
                SectorType.ALIEN);
        Sector sector2 = new Sector(CubicCoordinate.createFromAxial(1, 3),
                SectorType.HUMAN);

        sectors.add(sector1);
        sectors.add(sector2);

        Zone zone = new Zone(sectors);

        thrown.expect(IllegalStateException.class);

        zone.getHatchSectors();

    }

}
