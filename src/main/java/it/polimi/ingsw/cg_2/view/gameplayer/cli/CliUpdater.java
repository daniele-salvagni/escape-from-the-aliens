package it.polimi.ingsw.cg_2.view.gameplayer.cli;

import it.polimi.ingsw.cg_2.messages.broadcast.BroadcastMsg;
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

}
