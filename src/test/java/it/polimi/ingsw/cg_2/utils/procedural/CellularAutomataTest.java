package it.polimi.ingsw.cg_2.utils.procedural;

import java.io.IOException;

import it.polimi.ingsw.cg_2.utils.MapHelper;

import org.junit.Test;

public class CellularAutomataTest {

    
    @Test
    public void testDoThings() throws InterruptedException {

        CellularAutomata automata;

        try {

            for (int i = 0; i <= 700; i++) {
                // 2,5
                // 7,1

                automata = new CellularAutomata(0, 4, 20);
                automata.doThings();
                automata.getPixelRepresentation();
                MapHelper.saveMap(automata.getPixelRepresentation(),
                        "target/testmap/test-" + i + ".png");

            }

            /*
             * try {
             * 
             * for (int i = 0; i <= 12; i++) { for (int j = 0; j <= 12; j++) {
             * automata = new CellularAutomata(i, j); automata.doThings();
             * automata.getPixelRepresentation();
             * MapHelper.saveMap(automata.getPixelRepresentation(), "test-" + i
             * + "-" + j + ".png"); } }
             */

            /*
             * automata = new CellularAutomata(1, 1); automata.doThings();
             * automata.getPixelRepresentation();
             * MapHelper.saveMap(automata.getPixelRepresentation(),
             * "testtt.png");
             */

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void testDoThing2s() {

        CellularAutomata automata;

        try {

            for (int x = 10; x <= 80; x += 10) {
                for (int i = 0; i <= 12; i++) {
                    for (int j = 0; j <= 12; j++) {
                        automata = new CellularAutomata(i, j, x);
                        automata.doThings();
                        automata.getPixelRepresentation();
                        MapHelper.saveMap(automata.getPixelRepresentation(),
                                "target/testmap2/test-" + x + "-" + i + "-" + j
                                        + ".png");
                    }
                }
            }

            /*
             * automata = new CellularAutomata(1, 1); automata.doThings();
             * automata.getPixelRepresentation();
             * MapHelper.saveMap(automata.getPixelRepresentation(),
             * "testtt.png");
             */

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
