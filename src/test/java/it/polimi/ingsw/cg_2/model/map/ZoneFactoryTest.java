package it.polimi.ingsw.cg_2.model.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_2.model.map.Sector.SectorType;

import org.junit.BeforeClass;
import org.junit.Test;

public class ZoneFactoryTest {

	private static ZoneFactory zoneFactory;
	private static Zone zone;

	@BeforeClass
	public static void initClass() {

		zoneFactory = ZoneFactory.newLoader(ZoneName.DILEMMA);
		zone = zoneFactory.createZone();

	}

	@Test
	public void shouldCreateFactory() throws Exception {

		assertNotNull(zoneFactory);

	}

	@Test
	public void shouldCreateZone() {

		assertNotNull(zone);
	}

	@Test
	public void shouldCreateInstanceOfZoneLoader() {

		ZoneFactory zoneLoader = new ZoneLoader(ZoneName.DILEMMA);
		assertTrue(zoneLoader instanceof ZoneLoader);

	}

	@Test
	public void shouldCreateSector() {

		SectorType type = SectorType.ALIEN;
		Sector sector = zoneFactory.createSector(
				CubicCoordinate.create(0, 0, 0), type);

		assertNotNull(sector);
		assertEquals(type, sector.getType());
		assertEquals(CubicCoordinate.create(0, 0, 0), sector.getCooridnate());

	}

	@Test
	public void hatchSectorShouldBeCloseable() {

		/* Every hatch should be created of the subclass CloseableSector. */

		SectorType type = SectorType.HATCH;
		Sector sector = zoneFactory.createSector(
				CubicCoordinate.create(0, 0, 0), type);

		assertTrue(sector instanceof CloseableSector);

	}

}
