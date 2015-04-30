package it.polimi.ingsw.cg_2.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class provides static methods to load or save generated maps from/to a
 * .PNG file. To avoid unexpected behavior it is mandatory, if compression is
 * used, to provide only files using lossless compression.
 * <p>
 * The saved maps are in a format <b>easy to understand and edit</b> with the
 * most basic image processing software. Each sector is represented by a 2x2px
 * color square and each sector on an even column (counting from one) is shifted
 * down by 1px.
 * <p>
 * Any standard RGB color can be used, however it is recommended not to use
 * Black (#000000) which could be mistaken (by someone editing maps by hand)
 * with margins, but the way it would still be functioning perfectly). In this
 * class it is used the default RGB color model (TYPE_INT_ARGB).
 * 
 * @see http://i.imgur.com/lYzt2cx.png
 * @see BufferedImage#TYPE_INT_ARGB
 */
public class ZoneHelper {

    /**
     * Suppress the default constructor for noninstantiability (Effective Java -
     * Item 4).
     */
    private ZoneHelper() {
        throw new AssertionError();
    }
    
    // // PUBLIC METHODS // //

    public static int[][] loadZone(String filePath) {
        // TODO
        return null;
    }

    public static void saveZone(int[][] pixelMatrix, String filePath) {
        // TODO
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
    private static int[][] loadImage(String filePath) throws IOException {

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
    private static void writeImage(int[][] pixelMatrix, String filePath)
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
    private static int[][] addMargins(int[][] pixelMatrix) {

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
    private static int[][] removeMargins(int[][] pixelMatrix) {

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

    // // NORMALIZATION <-> DENORMALIZATION // //

    /**
     * This method scales a matrix by a x0.5 factor, for each 2x2 square only
     * the top-left pixel is kept, the rest is discarded.
     *
     * @param pixelMatrix the input matrix
     * @return the sampled matrix (size is /2)
     */
    private static int[][] samplePixels(int[][] pixelMatrix) {

        int imageWidth = pixelMatrix.length;
        int imageHeight = pixelMatrix[0].length;

        int[][] newPixelMatrix = new int[imageWidth / 2][imageHeight / 2];

        /* Loops trough every pixel on even row and column. */
        for (int x = 0; x < imageWidth; x += 2) {
            for (int y = 0; y < imageHeight; y += 2) {
                newPixelMatrix[x / 2][y / 2] = pixelMatrix[x][y];
            }
        }

        return newPixelMatrix;
    }

    /**
     * This method scales a matrix by a x2 factor.
     *
     * @param pixelMatrix the input matrix
     * @return the expanded matrix (size is x2)
     */
    private static int[][] expandPixels(int[][] pixelMatrix) {

        int imageWidth = pixelMatrix.length;
        int imageHeight = pixelMatrix[0].length;

        int[][] newPixelMatrix = new int[imageWidth * 2][imageHeight * 2];

        /* Expands each pixel into a 2x2 square. */
        for (int x = 0; x < imageWidth; x++) {
            for (int y = 0; y < imageHeight; y++) {
                newPixelMatrix[x * 2][y * 2] = pixelMatrix[x][y];
                newPixelMatrix[x * 2][y * 2 + 1] = pixelMatrix[x][y];
                newPixelMatrix[x * 2 + 1][y * 2] = pixelMatrix[x][y];
                newPixelMatrix[x * 2 + 1][y * 2 + 1] = pixelMatrix[x][y];
            }
        }

        return newPixelMatrix;
    }

}
