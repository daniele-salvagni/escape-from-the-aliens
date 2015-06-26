package it.polimi.ingsw.cg_2.view.gameplayer.cli;

import it.polimi.ingsw.cg_2.messages.broadcast.BroadcastMsg;
import it.polimi.ingsw.cg_2.messages.responses.ResponseMsg;
import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;
import it.polimi.ingsw.cg_2.view.gameplayer.ViewUpdater;

/**
 *
 */
public class CliUpdater implements ViewUpdater {

    MessageVisitor visitor;

    public CliUpdater() {

        visitor = new CliMessageVisitor();

    }

    @Override
    public void update(BroadcastMsg msg) {

        msg.display(visitor);

    }

    @Override
    public void update(ResponseMsg msg) {

        msg.display(visitor);

    }

    @Override
    public void setPlayerNum(int playerNum) {

        visitor.setPlayerNum(playerNum);

    }

    @Override
    public int getPlayerNum() {

        return visitor.getPlayerNum();

    }

}
