package it.polimi.ingsw.cg_2.messages.requests.actions;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.controller.actions.ActionFactoryVisitor;
import it.polimi.ingsw.cg_2.messages.Token;

/**
 * Message to request to perform a noise in a certain coordinate (DECEPTION).
 */
public class NoiseRequestMsg extends ActionRequestMsg {

    private final String coordinate;

    /**
     * Create a new NoiseRequestMsg request message.
     *
     * @param token the token that identifies the client
     * @param coordinate the coordinate where to make the noise in the format (COL:ROW)
     */
    public NoiseRequestMsg(Token token, String coordinate) {

        super(token);

        // Validates the XX:YY format, where XX and YY are 0-99
        if (!coordinate.matches("^(([0-9]|[1-9][0-9]):([0-9]|[1-9][0-9]))$")) {
            throw new IllegalArgumentException("Invalid coordinate format");
        }
        
        this.coordinate = coordinate;

    }

    /**
     * Get the coordinate where to make the noise in the format (COL:ROW).
     *
     * @return the coordinate where to make the noise in the format (COL:ROW)
     */
    public String getCoordinate() {

        return coordinate;

    }

    @Override
    public Action createAction(ActionFactoryVisitor visitor) {

        return visitor.visit(this);

    }

}
