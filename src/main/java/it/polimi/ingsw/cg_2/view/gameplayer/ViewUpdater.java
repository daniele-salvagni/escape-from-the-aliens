package it.polimi.ingsw.cg_2.view.gameplayer;

import it.polimi.ingsw.cg_2.messages.broadcast.BroadcastMsg;
import it.polimi.ingsw.cg_2.messages.responses.ResponseMsg;

/**
 *
 */
public interface ViewUpdater {

    void update(BroadcastMsg msg);

    void update(ResponseMsg msg);

    void setPlayerNum(int playerNum);

    int getPlayerNum();

}
