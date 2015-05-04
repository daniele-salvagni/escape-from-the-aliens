package it.polimi.ingsw.cg_2.utils;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class MapHelperTest {

    /**
     * Test the loading of a well-formed map.
     *
     * @throws Exception the exception
     */
    @Test
    public void testLoadNormalMap() throws Exception {

        /* Load the testing image. */
        String filePath = getClass().getResource(
                "/map/map-test-load-normal.png").getFile();
        int[][] loadedMatrix = MapHelper.loadMap(filePath);

        /* The expected matrix of pixels. */
        int[][] expectedMatrix = new int[][] { { 0xFF66CC66, 0xFF009966 },
                { 0xFF993333, 0xFF0099CC }, { 0xFF993399, 0xFF003333 } };

        assertTrue(Arrays.deepEquals(loadedMatrix, expectedMatrix));
    }

    /** Folder used to save temporary test files. */
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    /**
     * Test the saving on disk of a map.
     *
     * @throws Exception the exception
     */
    @Test
    public void testSaveNormalMap() throws Exception {

        /* The matrix we want to save. */
        int[][] inputMatrix = new int[][] { { 0xFF66CC66, 0xFF009966 },
                { 0xFF993333, 0xFF0099CC }, { 0xFF993399, 0xFF003333 } };

        /* Saves the testing image. */
        File outputFile = tempFolder.newFile("map-test-save-normal.png");
        String filePath = outputFile.getPath();

        MapHelper.saveMap(inputMatrix, filePath);

        /*
         * Use the already tested method loadMap on the created file and check
         * if it equals the input matrix.
         */
        int[][] savedMatrix = MapHelper.loadMap(filePath);

        assertTrue(Arrays.deepEquals(inputMatrix, savedMatrix));

    }

}
