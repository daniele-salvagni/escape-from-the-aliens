package it.polimi.ingsw.cg_2.messages.broadcast;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

/**
 * Created by dan on 19/06/15.
 */
public class UseTlpItemBroadcastMsg implements BroadcastMsg {

    private final int player;
    private final String coordinate;

    public UseTlpItemBroadcastMsg(int player, String coordinate) {

        this.player = player;
        this.coordinate = coordinate;

    }

    public int getPlayer() {

        return player;

    }

    public String getCoordinate() {

        return coordinate;

    }

    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
