package it.polimi.ingsw.cg_2.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * TODO: Complete javadoc This class will be used to load/save maps.
 */
public class ZoneHelper {

    /**
     * Suppress the default constructor for noninstantiability (Effective Java -
     * Item 4).
     */
    private ZoneHelper() {
        throw new AssertionError();
    }

    // // IMAGE I/O // //

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

    // // IMAGE PROCESSING // //

    /**
     * This method shifts down even columns of the hexagonal grid representation
     * to produce a human readable and modifiable image with a better
     * understanding of possible movements. The returned pixel matrix is 1px
     * higher than the one in input, margins are filled in black color.
     *
     * @param pixelMatrix the input matrix
     * @return a new matrix with shifted columns and 1px higher
     */
    public static int[][] addMargins(int[][] pixelMatrix) {

        int imageWidth = pixelMatrix.length;
        int imageHeight = pixelMatrix[0].length;

        int[][] newPixelMatrix = new int[imageWidth][imageHeight + 1];

        for (int x = 0; x < imageWidth; x++) {
            for (int y = 0; y < imageHeight; y++) {
                if ((x % 4 == 2) || (x % 4 == 3)) {

                    /*
                     * If columns 2, 3, 6, 7, 19, 11, 14, 15 ... then add a
                     * black pixel on the top and shift the rest of the column.
                     */

                    if (y == 0) {
                        newPixelMatrix[x][y] = 0x00000000;
                    }

                    newPixelMatrix[x][y + 1] = pixelMatrix[x][y];

                } else {

                    /* Else, just add a black pixel to the end. */

                    newPixelMatrix[x][y] = pixelMatrix[x][y];

                    if (y == imageHeight - 1) {
                        newPixelMatrix[x][y + 1] = 0x00000000;
                    }
                }

            }
        }

        return newPixelMatrix;
    }

    /**
     * This method shifts up even columns of the hexagonal grid representation
     * to remove the margins produced for better human understanding of possible
     * movements. The returned pixel matrix is 1px less high than the one in
     * input.
     *
     * @param pixelMatrix the input matrix
     * @return a new matrix with shifted back columns and 1px less high
     */
    public static int[][] removeMargins(int[][] pixelMatrix) {

        int imageWidth = pixelMatrix.length;
        int imageHeight = pixelMatrix[0].length;

        int[][] newPixelMatrix = new int[imageWidth][imageHeight - 1];

        for (int x = 0; x < imageWidth; x++) {
            for (int y = 0; y < imageHeight; y++) {
                if ((x % 4 == 2) || (x % 4 == 3)) {

                    /*
                     * If columns 2, 3, 6, 7, 19, 11, 14, 15 ... then copy the
                     * whole column except the first pixel and shift it up by 1.
                     */

                    if (y != 0) {
                        newPixelMatrix[x][y - 1] = pixelMatrix[x][y];
                    }

                } else {

                    /* Else, just copy the whole column except the last pixel. */

                    if (y != imageHeight - 1) {
                        newPixelMatrix[x][y] = pixelMatrix[x][y];
                    }

                }
            }

        }

        return newPixelMatrix;
    }

}
