package it.polimi.ingsw.cg_2.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * TODO: Complete javadoc
 * This class will be used to load/save maps.
 */
public class ZoneHelper {

    /**
     * Suppress the default constructor for noninstantiability (Effective Java -
     * Item 4).
     */
    private ZoneHelper() {
        throw new AssertionError();
    }

    /**
     * Loads an image from file into a bidimensional integer array of pixels in
     * the default RGB color model (TYPE_INT_ARGB).
     *
     * @param filePath the path of the file to be loaded
     * @return a bidimensional array representing the image
     * @throws IOException Signals that an I/O exception has occurred.
     * 
     * @see BufferedImage#TYPE_INT_ARGB
     */
    public static int[][] loadImage(String filePath) throws IOException {

        BufferedImage bufferImage = ImageIO.read(new File(filePath));

        int imageWidth = bufferImage.getWidth();
        int imageHeight = bufferImage.getHeight();

        int[][] pixelMatrix = new int[imageWidth][imageHeight];

        for (int x = 0; x < imageWidth; x++) {
            for (int y = 0; y < imageHeight; y++) {
                pixelMatrix[x][y] = bufferImage.getRGB(x, y);
            }
        }

        return pixelMatrix;
    }

    /**
     * Writes an image to disk from a bidimensional integer array of pixels in
     * the default RGB color model (TYPE_INT_ARGB).
     *
     * @param pixelMatrix an integer bidimensional array representing the image
     *            in the default RGB color model
     * @param filePath the path and the name of the file to be created
     * @throws IOException Signals that an I/O exception has occurred.
     * 
     * @see BufferedImage#TYPE_INT_ARGB
     */
    public static void writeImage(int[][] pixelMatrix, String filePath)
            throws IOException {

        int imageWidth = pixelMatrix.length;
        int imageHeight = pixelMatrix[0].length;

        BufferedImage bufferImage = new BufferedImage(imageWidth, imageHeight,
                BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < imageWidth; x++) {
            for (int y = 0; y < imageHeight; y++) {
                bufferImage.setRGB(x, y, pixelMatrix[x][y]);
            }
        }

        File outputFile = new File(filePath);
        ImageIO.write(bufferImage, "png", outputFile);
    }

}
