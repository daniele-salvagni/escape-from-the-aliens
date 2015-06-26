package it.polimi.ingsw.cg_2.messages.responses.actions;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

/**
 * A response message for the NoiseAction. Contains only a confirmation about
 * the location of the noise.
 */
public class NoiseResponseMsg extends ActionResponseMsg {

    private final String coordinate;

    /**
     * Create a nwe noiseRepsonseMsg.
     *
     * @param coordinate the position of the made noise
     */
    public NoiseResponseMsg(boolean success, String coordinate) {

        super(success);

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

    @Override
    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
