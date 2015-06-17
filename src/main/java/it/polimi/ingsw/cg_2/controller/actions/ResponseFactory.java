package it.polimi.ingsw.cg_2.controller.actions;

import it.polimi.ingsw.cg_2.messages.ResultMsgPair;
import it.polimi.ingsw.cg_2.messages.broadcast.BroadcastMsg;
import it.polimi.ingsw.cg_2.messages.responses.MoveResponseMsg;
import it.polimi.ingsw.cg_2.messages.responses.ResponseMsg;
import it.polimi.ingsw.cg_2.model.Game;
import it.polimi.ingsw.cg_2.model.map.Sector;
import it.polimi.ingsw.cg_2.model.player.Player;

import java.util.List;

/**
 * This static factory creates a pair of response messages (a private response
 * for the client that requested an action and a public one to boradcast to all
 * the clients of the game) after the execution of an action.
 */
public class ResponseFactory {

    /**
     * Create a {@link ResultMsgPair} for a {@link MoveAction}
     *
     * @param newPosition the sector destination of the move
     * @return the response message
     */
    protected static ResultMsgPair moveResponse(Sector newPosition) {

        ResponseMsg responseMsg;
        BroadcastMsg broadcastMsg;

        String coordinateStr = newPosition.getCooridnate().getOddQCol() + ":" +
                newPosition.getCooridnate().getOddQRow();

        String sectorTypeStr = newPosition.getType().name();

        responseMsg = new MoveResponseMsg(coordinateStr, sectorTypeStr);
        broadcastMsg = null; // Broadcast nothing

        return new ResultMsgPair(responseMsg, broadcastMsg);

    }

    protected static ResultMsgPair attackResponse(Game game, Player attacker,
            Sector position, Player... kills) {

        ResponseMsg responseMsg;
        BroadcastMsg broadcastMsg;

        List<Player> players = game.getPlayers();

        int attackerInt = players.indexOf(attacker);

        String coordinateStr = position.getCooridnate().getX() + ":" +
                position.getCooridnate().getZ();

        int[] killsInt = new int[kills.length];
        for (int i = 0; i < kills.length; i++) {
            killsInt[i] = players.indexOf(kills[i]);
        }

        responseMsg = new AttackResponse(coordinateStr, killsInt);
        broadcastMsg = new AttackBroadcast(attackerInt, coordinateStr,
                killsInt);

        return new ResultMsgPair(responseMsg, broadcastMsg);

    }


}
