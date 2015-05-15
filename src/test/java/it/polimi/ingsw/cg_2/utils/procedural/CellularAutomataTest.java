package it.polimi.ingsw.cg_2.utils.procedural;

import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import it.polimi.ingsw.cg_2.model.map.CubicCoordinate;

public class CellularAutomataTest {

    @Ignore("Manual testing")
    @Test
    public void testDoThings() throws InterruptedException {

        /* TODO: At the current state this is just for testing purposes */

        // 0,5-20
        // 10,3-70
        // 5,2-70
        // INNER 85!!!

        Seed seed = new AlteredSeed(70, 23, 14, 85, 8, 6);

        CellularAutomata automata = new CellularAutomata(5, 2,
                seed.nextGrid(321321));

        automata.applyTicks(3);

        // We need a mapHelperAdpter so we can pass directly automata.getGrid()
        // and avoid imports
        Map<CubicCoordinate, CellStatus> grid = automata.getGrid();

        // Process it
        // GridProcessor.replaceSmallerClusters(grid, ALIVE, DEAD);

        /*
         * try { MapHelper.saveMap(automata.toPixelRepresentation(),
         * "target/testmapX/test-" + 3 + ".png"); } catch (IOException e) { //
         * TODO Auto-generated catch block e.printStackTrace(); }
         */
    }

}
