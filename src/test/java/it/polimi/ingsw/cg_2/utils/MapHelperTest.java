package it.polimi.ingsw.cg_2.utils;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

public class MapHelperTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    // // WELL FORMED ARGUMENTS // //

    /**
     * Test the loading of a well-formed map. It should match the expected
     * matrix.
     */
    @Test
    public void shouldLoadWellFormedMap() throws Exception {

        /* Load the testing image into a matrix. */
        String filePath = getClass().getResource(
                "/map/map-test-load-normal.png").getFile();
        int[][] loadedMatrix = MapHelper.loadMap(filePath);

        /* The expected matrix of pixels. */
        int[][] expectedMatrix = new int[][] { { 0xFF66CC66, 0xFF009966 },
                { 0xFF993333, 0xFF0099CC }, { 0xFF993399, 0xFF003333 } };

        assertTrue(Arrays.deepEquals(loadedMatrix, expectedMatrix));
    }

    /**
     * Test the saving on disk of a map. After saving the map we load it and the
     * result should match.
     */
    @Test
    public void shouldWriteWellFormedMap() throws Exception {

        /* The matrix we want to transform and save. */
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

    // // NULL ARGUMENTS // //

    /**
     * Test the (@link MapHelper) loadMap method with a null argument.
     */
    @Test
    public void shouldFailToLoadNull() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        MapHelper.loadMap(null);
    }

    /**
     * Test the (@link MapHelper) saveMap method with null arguments.
     */
    @Test
    public void shouldFailToWriteNull() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        MapHelper.saveMap(null, null);
    }

    // // NOT WELL FORMED ARGUMENTS // //

    /**
     * Test the saving on disk of a not well formed 2D array. Should throw
     * IllegalArgumentException.
     */
    @Test
    public void shouldFailToWriteNotWellFormedArray() throws Exception {
        thrown.expect(IllegalArgumentException.class);

        /* A column has 3 pixels instead of 2. */
        int[][] inputMatrix = new int[][] { { 0xFF66CC66, 0xFF009966 },
                { 0xFF993333, 0xFF0099CC, 0xFF993333 },
                { 0xFF993399, 0xFF003333 } };

        File outputFile = tempFolder.newFile("map-not-well-formed.png");
        String filePath = outputFile.getPath();

        MapHelper.saveMap(inputMatrix, filePath);
    }

    /**
     * Try to load a map with an odd width in pixels.
     */
    @Test
    public void shouldFailToLoadOddWidth() throws Exception {
        thrown.expect(IllegalArgumentException.class);

        /* This file has an odd width in pixels. */
        String filePath = getClass().getResource(
                "/map/map-test-error-oddwidth.png").getFile();

        MapHelper.loadMap(filePath);
    }

    /**
     * Try to load a map with an even height in pixels.
     */
    @Test
    public void shouldFailToLoadEvenHeight() throws Exception {
        thrown.expect(IllegalArgumentException.class);

        /* This file has an even height in pixels. */
        String filePath = getClass().getResource(
                "/map/map-test-error-evenheight.png").getFile();

        MapHelper.loadMap(filePath);
    }

    /**
     * Try to load an invalid image (not an image).
     */
    @Test
    public void shouldFailToLoadInvalidFile() throws Exception {
        thrown.expect(IllegalArgumentException.class);

        /* This image is not valid (it is not an image). */
        String filePath = getClass().getResource("/map/map-invalid.png")
                .getFile();
        MapHelper.loadMap(filePath);
    }

}
