package it.polimi.ingsw.cg_2.messages.responses;

import it.polimi.ingsw.cg_2.messages.broadcast.BroadcastMsg;
import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class PublicLogResponseMsg implements ResponseMsg {

    private final ArrayList<BroadcastMsg> log;

    public PublicLogResponseMsg(List<BroadcastMsg> log) {

        this.log = new ArrayList<>(log);

    }

    public List<BroadcastMsg> getLog() {

        return log;

    }

    @Override
    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
