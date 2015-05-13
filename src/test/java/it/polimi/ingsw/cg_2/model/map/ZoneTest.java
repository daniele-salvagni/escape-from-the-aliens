package it.polimi.ingsw.cg_2.model.map;

import java.util.HashSet;
import java.util.Set;

import it.polimi.ingsw.cg_2.model.map.Sector.SectorType;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ZoneTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldBeCreated() {

        // TODO
    }

    @Test
    public void shouldThrowExceptionIfTwoSectorsHaveTheSameCoordinate() {

        Set<Sector> sectors = new HashSet<Sector>();

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
