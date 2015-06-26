package it.polimi.ingsw.cg_2.messages.requests.actions;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.controller.actions.ActionFactoryVisitor;
import it.polimi.ingsw.cg_2.messages.Token;

/**
 * A message to request to move to a certain coordinate.
 */
public class MoveRequestMsg extends ActionRequestMsg {

    private final String coordinate;

    /**
     * Create anew MoveRequestMsg request message.
     *
     * @param token the token that identifies the client
     * @param coordinate the coordinate where to move in the format (COL:ROW)
     */
    public MoveRequestMsg(Token token, String coordinate) {

        super(token);

        // Validates the XX:YY format, where XX and YY are 0-99
        if (!coordinate.matches("^(([0-9]|[1-9][0-9]):([0-9]|[1-9][0-9]))$")) {
            throw new IllegalArgumentException("Invalid coordinate format");
        }

        this.coordinate = coordinate;

    }

    /**
     * Get the destination coordinate of the movement in the format (COL:ROW).
     *
     * @return the destination coordinate of the movement in the format (COL:ROW)
     */
    public String getCoordinate() {

        return coordinate;

    }

    @Override
    public Action createAction(ActionFactoryVisitor visitor) {

        return visitor.visit(this);

    }

}
