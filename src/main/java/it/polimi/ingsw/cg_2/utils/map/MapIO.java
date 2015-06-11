package it.polimi.ingsw.cg_2.utils.map;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.imageio.ImageIO;

/**
 * This class provides static methods to load or save generated maps from/to a
 * .PNG file. To avoid unexpected behavior it is mandatory, if compression is
 * used, to provide only files using lossless compression.
 * <p/>
 * The saved maps are in a format <b>easy to understand and edit</b> with the
 * most basic image processing software. Each sector is represented by a 2x2px
 * color square and each sector on an even column (counting from one) is
 * shifted
 * down by 1px.
 * <p/>
 * Any standard RGB color can be used, however it is recommended not to use
 * Black (#000000) which could be mistaken (by someone editing maps by hand)
 * with margins, but the way it would still be functioning perfectly). In this
 * class it is used the default RGB color model (TYPE_INT_ARGB).
 *
 * @see <a href="http://i.imgur.com/lYzt2cx.png">Image transformation
 * process</a>
 * @see BufferedImage#TYPE_INT_ARGB
 */
public class MapIO {

    private static final int ARGB_BLACK = 0xFF000000;

    /**
     * Suppress the default constructor for noninstantiability (Effective Java
     * -
     * Item 4).
     */
    private MapIO() {

        throw new AssertionError();

    }

    // // PUBLIC METHODS // //

    /**
     * Loads a map from a human readable and editable image format into a 2D
     * array in offset coordinate system (odd-q).
     *
     * @param filePath the file path of the image map
     * @return a 2D array representing the map in offset coordinate system
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static int[][] loadMap(String filePath) throws IOException {

        if (filePath == null) {
            throw new IllegalArgumentException("filePath must be non-null.");
        }

        int[][] image = loadImage(filePath);

        /*
         * Check if image width is even and height is odd. It would work anyways
         * but we don't want this as it could lead to subtle errors or
         * misconceptions.
         */
        if (image.length % 2 != 0) {
            throw new IllegalArgumentException("Image width should be even.");
        } else if (image[0].length % 2 != 1) {
            throw new IllegalArgumentException("Image height should be odd.");
        }

        int[][] imageWithoutMargins = removeMargins(image);
        return samplePixels(imageWithoutMargins);

    }

    /**
     * Saves a map from a 2D array in offset coordinate system (odd-q) to an
     * human readable and editable image format. 2D array must be rectangular
     * (every sub-array must have the same length).
     *
     * @param pixelMatrix a 2D array representing the map in offset coordinate
     *                    system
     * @param filePath    the file path where to save the image
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void saveMap(int[][] pixelMatrix, String filePath)
            throws IOException {

        if (filePath == null || pixelMatrix == null) {
            throw new IllegalArgumentException("Arguments must be not-null.");
        } else if (!isMatrixRectangular(pixelMatrix)) {
            throw new IllegalArgumentException(
                    "Input 2D array should be rectangular.");
        }

        int[][] expandedImage = expandPixels(pixelMatrix);
        int[][] imageWithMargins = addMargins(expandedImage);
        writeImage(imageWithMargins, filePath);

    }

    // // IMAGE I/O // //

    /**
     * Loads an image from file into a bidimensional integer array of pixels in
     * the default RGB color model (TYPE_INT_ARGB).
     *
     * @param filePath the path of the file to be loaded
     * @return a bidimensional array representing the image
     *
     * @throws IOException Signals that an I/O exception has occurred.
     * @see BufferedImage#TYPE_INT_ARGB
     */
    private static int[][] loadImage(String filePath) throws IOException {

        BufferedImage bufferImage = ImageIO.read(new File(URLDecoder.decode
                (filePath, "UTF-8")));

        /* ImageIO returns null if the provided file is not a valid image. */
        if (bufferImage == null) {
            throw new IllegalArgumentException("Input file must be an image.");
        }

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
     *                    in the default RGB color model
     * @param filePath    the path and the name of the file to be created
     * @throws IOException Signals that an I/O exception has occurred.
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
     * This method shifts up even columns of the hexagonal grid representation
     * to remove the margins produced for better human understanding of
     * possible
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

                    /* Else, just copy the whole column except the last pixel
                    . */

                    if (y != imageHeight - 1) {
                        newPixelMatrix[x][y] = pixelMatrix[x][y];
                    }

                }

            }
        }

        return newPixelMatrix;

    }

    /**
     * This method shifts down even columns of the hexagonal grid
     * representation
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
                        newPixelMatrix[x][y] = ARGB_BLACK;
                    }

                    newPixelMatrix[x][y + 1] = pixelMatrix[x][y];

                } else {

                    /* Else, just add a black pixel to the end. */

                    newPixelMatrix[x][y] = pixelMatrix[x][y];

                    if (y == imageHeight - 1) {
                        newPixelMatrix[x][y + 1] = ARGB_BLACK;
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

        if (imageWidth < 2 || imageHeight < 2) {
            throw new IllegalArgumentException(
                    "pixelMatrix must be at least 2x2px.");
        }

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

    /**
     * Checks if the given matrix is rectangular. (It is not a matrix, but an
     * array of arrays).
     *
     * @param pixelMatrix the matrix to check
     * @return true, if is the matrix is rectangular
     */
    private static boolean isMatrixRectangular(int[][] pixelMatrix) {

        for (int[] column : pixelMatrix) {
            if (pixelMatrix[0].length != column.length) {
                return false;
            }
        }

        return true;

    }

}
