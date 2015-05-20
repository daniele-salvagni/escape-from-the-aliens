package it.polimi.ingsw.cg_2.model.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_2.model.map.Sector.SectorType;

import org.junit.BeforeClass;
import org.junit.Test;

public class ZoneFactoryTest {

    private static ZoneFactory zoneLoader;
    private static Zone zone;

    @BeforeClass
    public static void initClass() {

        zoneLoader = new ZoneLoader(ZoneName.DILEMMA);
        zone = zoneLoader.createZone();

    }


}
