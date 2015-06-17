package it.polimi.ingsw.cg_2.messages.responses;

/**
 * A response message for the noiseAction. Contains only a confirmation about
 * the location of the noise.
 */
public class NoiseResponseMsg implements ResponseMsg {

    private final String coordinate;

    /**
     * Create a nwe noiseRepsonseMsg.
     *
     * @param coordinate the position of the made noise
     */
    public NoiseResponseMsg(String coordinate) {

        if (coordinate == null) {

            throw new IllegalArgumentException("coordinate cannot be null");

        }

        this.coordinate = coordinate;

    }

    /**
     * Get the position of the made noise
     *
     * @return the position of the made noise
     */
    public String getCoordinate() {

        return coordinate;
    }

}
