package it.polimi.ingsw.cg_2.controller.actions;

import it.polimi.ingsw.cg_2.messages.ResultMsgPair;
import it.polimi.ingsw.cg_2.messages.broadcast.AttackBroadcastMsg;
import it.polimi.ingsw.cg_2.messages.broadcast.BroadcastMsg;
import it.polimi.ingsw.cg_2.messages.responses.AttackResponseMsg;
import it.polimi.ingsw.cg_2.messages.responses.DrawResponseMsg;
import it.polimi.ingsw.cg_2.messages.responses.MoveResponseMsg;
import it.polimi.ingsw.cg_2.messages.responses.ResponseMsg;
import it.polimi.ingsw.cg_2.model.Game;
import it.polimi.ingsw.cg_2.model.deck.ItemCard;
import it.polimi.ingsw.cg_2.model.deck.SectorCard;
import it.polimi.ingsw.cg_2.model.map.CubicCoordinate;
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

    /**
     * @param game
     * @param attacker
     * @param position
     * @param kills     null
     * @param survivors null
     * @return
     */
    protected static ResultMsgPair attackResponse(Game game, Player attacker,
            Sector position, List<Player> kills, List<Player> survivors) {

        ResponseMsg responseMsg;
        BroadcastMsg broadcastMsg;

        List<Player> players = game.getPlayers();

        int attackerInt = players.indexOf(attacker);

        String coordinateStr = position.getCooridnate().getX() + ":" +
                position.getCooridnate().getZ();

        int[] killsInt = new int[kills.size()];
        int[] survivorsInt = new int[survivors.size()];

        for (int i = 0; i < kills.size(); i++) {
            killsInt[i] = players.indexOf(kills.get(i));
        }

        for (int i = 0; i < survivors.size(); i++) {
            survivorsInt[i] = players.indexOf(survivors.get(i));
        }

        responseMsg = new AttackResponseMsg(coordinateStr, killsInt,
                survivorsInt);
        broadcastMsg = new AttackBroadcastMsg(attackerInt, coordinateStr,
                killsInt, survivorsInt);

        return new ResultMsgPair(responseMsg, broadcastMsg);

    }

    /**
     * @param game
     * @param player
     * @param sectorCard
     * @param itemCard   null
     * @return
     */
    protected static ResultMsgPair drawResponse(Game game, Player player,
            SectorCard sectorCard, ItemCard itemCard) {

        ResponseMsg responseMsg;
        BroadcastMsg broadcastMsg;

        List<Player> players;
        int playerInt;
        String cardTypeStr;
        String itemTypeStr;
        boolean foundItemBool;
        String coordinateStr;


        players = game.getPlayers();

        playerInt = players.indexOf(player);
        cardTypeStr = sectorCard.getType().name();

        if (itemCard != null) {
            itemTypeStr = itemCard.getType().name();
            foundItemBool = true;
        } else {
            itemTypeStr = null;
            foundItemBool = false;
        }

        Sector position = player.getCharacter().getPosition();
        coordinateStr = position.getCooridnate().getX() + ":" +
                position.getCooridnate().getZ();

        responseMsg = new DrawResponseMsg(cardTypeStr, itemTypeStr);

        if (sectorCard.getType() == SectorCard.SectorCardType.DECEPTION) {
            // Do not say anything to others, we need to wait the noise
            // position from the player
            broadcastMsg = null;
        } else if (sectorCard.getType() == SectorCard.SectorCardType.NOISE) {
            broadcastMsg = new NoiseBroadcastMsg(playerInt, cardTypeStr,
                    coordinateStr, foundItemBool);
        } else { // SILENCE
            broadcastMsg = new NoiseBroadcastMsg(playerInt, cardTypeStr,
                    null, foundItemBool);
        }

        return new ResultMsgPair(responseMsg, broadcastMsg);

    }


}
