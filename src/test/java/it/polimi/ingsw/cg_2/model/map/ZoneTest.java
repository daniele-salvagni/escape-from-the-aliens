package it.polimi.ingsw.cg_2.model.map;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import it.polimi.ingsw.cg_2.model.map.Sector.SectorType;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ZoneTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Set<Sector> sectors;
    private CubicCoordinate coord1;
    private CubicCoordinate coord2;
    private Sector sector1;
    private Sector sector2;
    private Zone zone;

    @Before
    public void init() {

        sectors = new HashSet<Sector>();

        coord1 = CubicCoordinate.createFromAxial(1, 2);
        coord2 = CubicCoordinate.createFromAxial(1, 3);

        sector1 = new Sector(coord1, SectorType.SAFE);
        sector2 = new Sector(coord2, SectorType.DANGEROUS);

        sectors.add(sector1);
        sectors.add(sector2);

        zone = new Zone(sectors);

    }

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

        Map<CubicCoordinate, Sector> sectorMap = zone.getSectorsMap();

        // Should not be null
        assertNotNull(sectorMap);
        // Key-Value pairs should be correct
        assertEquals(sector1, sectorMap.get(coord1));
        assertEquals(sector2, sectorMap.get(coord2));
        // Should contain the correct number of items
        assertEquals(sectors.size(), sectorMap.size());

    }

    @Test
    public void shouldGetSectorFromCoordinate() {

        assertEquals(sector1, zone.getSector(coord1));
        assertEquals(sector2, zone.getSector(coord2));

    }

    @Test
    public void shouldGetZoneCoordinates() {

        Set<CubicCoordinate> coordinates = zone.getCoordinates();

        assertTrue(coordinates.contains(coord1));
        assertTrue(coordinates.contains(coord2));
        assertEquals(sectors.size(), coordinates.size());

    }

    // // EXCEPTIONS // //

    @Test
    public void shouldThrowExceptionIfTwoSectorsHaveTheSameCoordinate() {

        // We add two sectors with the same coordinate
        Sector sector1 = new Sector(CubicCoordinate.createFromAxial(0, 0),
                SectorType.ALIEN);
        Sector sector2 = new Sector(CubicCoordinate.createFromAxial(0, 0),
                SectorType.HUMAN);

        sectors.add(sector1);
        sectors.add(sector2);

        thrown.expect(IllegalArgumentException.class);

        new Zone(sectors);

    }

    @Test
    public void shouldThrowExceptionIfWrongNumberOfAlienSectors() {

        // We add two alien sectors
        Sector sector1 = new Sector(CubicCoordinate.createFromAxial(1, 4),
                SectorType.ALIEN);
        Sector sector2 = new Sector(CubicCoordinate.createFromAxial(1, 5),
                SectorType.ALIEN);

        sectors.add(sector1);
        sectors.add(sector2);

        Zone zone = new Zone(sectors);

        thrown.expect(IllegalStateException.class);

        zone.getAlienSector();

    }

    @Test
    public void shouldThrowExceptionIfWrongNumberOfHumanSectors() {

        // We add two human sectors
        Sector sector1 = new Sector(CubicCoordinate.createFromAxial(1, 4),
                SectorType.HUMAN);
        Sector sector2 = new Sector(CubicCoordinate.createFromAxial(1, 5),
                SectorType.HUMAN);

        sectors.add(sector1);
        sectors.add(sector2);

        Zone zone = new Zone(sectors);

        thrown.expect(IllegalStateException.class);

        zone.getHumanSector();

    }

    @Test
    public void shouldThrowExceptionIfNoHatches() {

        thrown.expect(IllegalStateException.class);

        zone.getHatchSectors();

    }

}
