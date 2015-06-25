package it.polimi.ingsw.cg_2.messages.responses.actions;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

/**
 *
 */
public class UseTlpItemResponseMsg extends ActionResponseMsg {

    private final String coordinate;

    public UseTlpItemResponseMsg(boolean success, String coordinate) {

        super(success);

        this.coordinate = coordinate;

    }

    public String getCoordinate() {

        return coordinate;

    }

    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
