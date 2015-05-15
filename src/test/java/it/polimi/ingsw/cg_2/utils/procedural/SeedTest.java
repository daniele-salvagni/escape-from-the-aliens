package it.polimi.ingsw.cg_2.utils.procedural;

import static org.junit.Assert.*;

import java.util.Map;

import it.polimi.ingsw.cg_2.model.map.CubicCoordinate;

import org.junit.Test;

/**
 * WARNING: The tested class involves randomness, so we can't exactly check if
 * the output is correct, to properly test those methods a statistical analysis
 * should be done instead.
 */
public class SeedTest {

    @Test
    public void shouldGenerateAlteredSeed() {

        long noSeed = -1;
        Map<CubicCoordinate, CellStatus> seed = Seed
                .generateAlteredSeed(100, 0, CellStatus.ALIVE, CellStatus.DEAD,
                        noSeed);

        /*
         * We did set a 100% probability in the outside and a 0% probability in
         * the inside, so it should always contain alive and dead cells.
         */

        assertTrue(seed.containsValue(CellStatus.ALIVE));
        assertTrue(seed.containsValue(CellStatus.DEAD));

    }

    @Test
    public void shouldGenerateUnalteredSeed() {

        long noSeed = -1;
        Map<CubicCoordinate, CellStatus> seed = Seed
                .generateAlteredSeed(100, -1, CellStatus.ALIVE,
                        CellStatus.DEAD, noSeed);

        assertTrue(seed.containsValue(CellStatus.ALIVE));
        assertFalse(seed.containsValue(CellStatus.DEAD));

    }

    @Test
    public void shouldAcceptSeed() {

        assertNotNull(Seed.generateAlteredSeed(30, 85,
                CellStatus.ALIVE, CellStatus.DEAD, 123));
    }
}
