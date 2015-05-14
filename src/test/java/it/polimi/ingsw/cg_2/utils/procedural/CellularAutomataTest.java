package it.polimi.ingsw.cg_2.utils.procedural;

import java.io.IOException;

import it.polimi.ingsw.cg_2.utils.MapHelper;

import org.junit.Test;

public class CellularAutomataTest {

    @Test
    public void testDoThings() throws InterruptedException {

        CellularAutomata automata;

        try {

            for (int i = 0; i <=700; i++) {
                automata = new CellularAutomata(2, 5);
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

    @Test
    public void testDoThing2s() {

        CellularAutomata automata;

        try {

            for (int i = 0; i <= 12; i++) {
                for (int j = 0; j <= 12; j++) {
                    automata = new CellularAutomata(i, j);
                    automata.doThings();
                    automata.getPixelRepresentation();
                    MapHelper.saveMap(automata.getPixelRepresentation(),
                            "target/testmap2/test-" + i + "-" + j + ".png");
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
