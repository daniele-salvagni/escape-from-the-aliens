package it.polimi.ingsw.cg_2.view.gameplayer;

import it.polimi.ingsw.cg_2.messages.broadcast.BroadcastMsg;
import it.polimi.ingsw.cg_2.messages.responses.ResponseMsg;

/**
 * An interface to update the view of a game.
 */
public interface ViewUpdater {

    /**
     * update the view with a received broadcast message.
     *
     * @param msg the received broadcast message
     */
    void update(BroadcastMsg msg);

    /**
     * Update the view with a received response message
     *
     * @param msg the received response message
     */
    void update(ResponseMsg msg);

    /**
     * Set the player number of this client in the game, it is used to filter out some
     * broadcast messages.
     *
     * @param playerNum the player number of this client in the game
     */
    void setPlayerNum(int playerNum);

    /**
     * Get the player number of this client in the game.
     *
     * @return the player number of this client in the game
     */
    int getPlayerNum();

}
