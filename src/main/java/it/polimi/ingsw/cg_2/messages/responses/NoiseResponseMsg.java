package it.polimi.ingsw.cg_2.messages.responses;

/**
 * A response message for the noiseAction. Contains only a confirmation about
 * the location of the noise.
 */
public class NoiseResponseMsg implements ResponseMsg {

    private final String coordinate;

    public NoiseResponseMsg(String coordinate) {

        if (coordinate == null) {

            throw new IllegalArgumentException("coordinate cannot be null");

        }

        this.coordinate = coordinate;

    }

    public String getCoordinate() {

        return coordinate;
    }

}
