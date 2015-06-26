package it.polimi.ingsw.cg_2.messages.requests.actions;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.controller.actions.ActionFactoryVisitor;
import it.polimi.ingsw.cg_2.messages.Token;

/**
 * A message to request to use a SPOTLIGHT item.
 */
public class UseSptRequestMsg extends ActionRequestMsg {

    private final String coordinate;

    /**
     * Create a new useAdrRequestMsg request message.
     *
     * @param token the token that identifies the client
     * @param coordinate the coordinate where to use the item in the COL:ROW format
     */
    public UseSptRequestMsg(Token token, String coordinate) {

        super(token);

        // Validates the XX:YY format, where XX and YY are 0-99
        if (!coordinate.matches("^(([0-9]|[1-9][0-9]):([0-9]|[1-9][0-9]))$")) {
            throw new IllegalArgumentException("Invalid coordinate format");
        }

        this.coordinate = coordinate;

    }

    /**
     * Get the coordinate where to use the item in the COL:ROW format.
     *
     * @return the coordinate where to use the item in the COL:ROW format
     */
    public String getCoordinate() {

        return coordinate;

    }

    @Override
    public Action createAction(ActionFactoryVisitor visitor) {

        return visitor.visit(this);

    }

}
