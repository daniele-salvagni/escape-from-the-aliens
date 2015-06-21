package it.polimi.ingsw.cg_2.messages.requests.actions;

import it.polimi.ingsw.cg_2.controller.actions.Action;
import it.polimi.ingsw.cg_2.controller.actions.ActionFactoryVisitor;

/**
 *
 */
public class MoveRequestMsg extends ActionRequestMsg {

    private final String coordinate;

    public MoveRequestMsg(String token, String coordinate) {

        super(token);
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
