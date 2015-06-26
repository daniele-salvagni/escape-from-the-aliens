package it.polimi.ingsw.cg_2.messages.requests.actions;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.controller.actions.ActionFactoryVisitor;
import it.polimi.ingsw.cg_2.messages.Token;

/**
 *
 */
public class UseSptRequestMsg extends ActionRequestMsg {

    private final String coordinate;

    public UseSptRequestMsg(Token token, String coordinate) {

        super(token);

        // Validates the XX:YY format, where XX and YY are 0-99
        if (!coordinate.matches("^(([0-9]|[1-9][0-9]):([0-9]|[1-9][0-9]))$")) {
            throw new IllegalArgumentException("Invalid coordinate format");
        }

        this.coordinate = coordinate;

    }

    public String getCoordinate() {

        return coordinate;

    }

    @Override
    public Action createAction(ActionFactoryVisitor visitor) {

        return visitor.visit(this);

    }

}
