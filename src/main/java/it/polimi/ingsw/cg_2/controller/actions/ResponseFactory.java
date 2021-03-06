package it.polimi.ingsw.cg_2.controller.actions;

import it.polimi.ingsw.cg_2.messages.ResultMsgPair;
import it.polimi.ingsw.cg_2.messages.broadcast.*;
import it.polimi.ingsw.cg_2.messages.responses.*;
import it.polimi.ingsw.cg_2.messages.responses.actions.*;
import it.polimi.ingsw.cg_2.model.Game;
import it.polimi.ingsw.cg_2.model.deck.HatchCard;
import it.polimi.ingsw.cg_2.model.deck.ItemCard;
import it.polimi.ingsw.cg_2.model.deck.SectorCard;
import it.polimi.ingsw.cg_2.model.map.Sector;
import it.polimi.ingsw.cg_2.model.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This static factory creates a pair of response messages (a private response
 * for the client that requested an action and a public one to boradcast to all
 * the clients of the game) after the execution of an action.
 */
public class ResponseFactory {

    /**
     * Suppress the default constructor for noninstantiability.
     */
    private ResponseFactory() {

        throw new AssertionError();
    }

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

        responseMsg = new MoveResponseMsg(true, coordinateStr, sectorTypeStr);
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
                                                  Sector position,
                                                  List<Player> kills,
                                                  List<Player> survivors) {

        ResponseMsg responseMsg;
        BroadcastMsg broadcastMsg;

        int attackerInt = game.getPlayerNumber(attacker);

        String coordinateStr = position.getCooridnate().getOddQCol() + ":" +
                position.getCooridnate().getOddQRow();

        Map<Integer, String> killsIntMap = new HashMap<>();
        List<Integer> survivorsIntList = new ArrayList<>();

        if (kills != null) {
            for (Player kill : kills) {

                int killInt = game.getPlayerNumber(kill);
                String killRaceStr = kill.getCharacter().getRace().name();
                killsIntMap.put(killInt, killRaceStr);

            }
        }

        if (survivors != null) {
            for (Player survivor : survivors) {

                int survivorInt = game.getPlayerNumber(survivor);
                survivorsIntList.add(survivorInt);

            }
        }

        responseMsg = new AttackResponseMsg(true, coordinateStr, killsIntMap,
                survivorsIntList);
        broadcastMsg = new AttackBroadcastMsg(attackerInt, coordinateStr,
                killsIntMap, survivorsIntList);

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
                                                SectorCard sectorCard,
                                                ItemCard itemCard) {

        ResponseMsg responseMsg;
        BroadcastMsg broadcastMsg;

        int playerInt;
        String cardTypeStr;
        String itemTypeStr;
        boolean foundItemBool;
        String coordinateStr;

        playerInt = game.getPlayerNumber(player);
        cardTypeStr = sectorCard.getType().name();

        if (itemCard != null) {
            itemTypeStr = itemCard.getType().name();
            foundItemBool = true;
        } else {
            itemTypeStr = "";
            foundItemBool = false;
        }

        Sector position = player.getCharacter().getPosition();
        coordinateStr = position.getCooridnate().getOddQCol() + ":" +
                position.getCooridnate().getOddQRow();

        responseMsg = new DrawResponseMsg(true, cardTypeStr, itemTypeStr);

        if (sectorCard.getType() == SectorCard.SectorCardType.DECEPTION) {
            // Do not say anything to others, we need to wait the noise
            // position from the player
            broadcastMsg = null;
        } else if (sectorCard.getType() == SectorCard.SectorCardType.NOISE) {
            broadcastMsg = new NoiseBroadcastMsg(playerInt, cardTypeStr,
                    coordinateStr, foundItemBool);
        } else { // SILENCE
            broadcastMsg = new NoiseBroadcastMsg(playerInt, cardTypeStr,
                    "", foundItemBool);
        }

        return new ResultMsgPair(responseMsg, broadcastMsg);

    }

    /**
     * @param game
     * @param player
     * @param position
     * @param item
     * @return
     */
    protected static ResultMsgPair noiseResponse(Game game, Player player,
                                                 Sector position, boolean
                                                         item) {

        ResponseMsg responseMsg;
        BroadcastMsg broadcastMsg;

        int playerInt;
        String cardTypeStr;
        String coordinateStr;

        playerInt = game.getPlayerNumber(player);
        // We don't broadcast the fact that it was a deception noise, only
        // that it is a "NOISE"
        cardTypeStr = SectorCard.SectorCardType.NOISE.name();
        coordinateStr = position.getCooridnate().getOddQCol() + ":" +
                position.getCooridnate().getOddQRow();

        responseMsg = new NoiseResponseMsg(true, coordinateStr);

        broadcastMsg = new NoiseBroadcastMsg(playerInt, cardTypeStr,
                coordinateStr, item);

        return new ResultMsgPair(responseMsg, broadcastMsg);

    }

    /**
     * @param game
     * @param player
     * @param hatchCard
     * @param newTurn    -1
     * @param nextPlayer null
     * @return
     */
    protected static ResultMsgPair escapeResponse(Game game, Player player,
                                                  HatchCard hatchCard, int
                                                          newTurn, Player
                                                          nextPlayer) {

        ResponseMsg responseMsg;
        BroadcastMsg broadcastMsg;

        int playerInt;
        String cardTypeStr;
        String coordinateStr;
        int nextPlayerInt;

        Sector position = player.getCharacter().getPosition();

        playerInt = game.getPlayerNumber(player);
        cardTypeStr = hatchCard.getType().name();
        coordinateStr = position.getCooridnate().getOddQCol() + ":" +
                position.getCooridnate().getOddQRow();

        if (nextPlayer != null) {
            nextPlayerInt = game.getPlayerNumber(nextPlayer);
        } else {
            nextPlayerInt = -1;
        }

        responseMsg = new EscapeResponseMsg(true, cardTypeStr, coordinateStr,
                newTurn, nextPlayerInt);

        broadcastMsg = new EscapeBroadcastMsg(playerInt, cardTypeStr,
                coordinateStr, newTurn, nextPlayerInt);

        return new ResultMsgPair(responseMsg, broadcastMsg);

    }

    /**
     * @param game
     * @param player
     * @param newTurn
     * @param nextPlayer
     * @return
     */
    protected static ResultMsgPair passResponse(Game game, Player player, int
            newTurn, Player nextPlayer) {

        ResponseMsg responseMsg;
        BroadcastMsg broadcastMsg;

        int playerInt;
        int nextPlayerInt;

        playerInt = game.getPlayerNumber(player);
        nextPlayerInt = game.getPlayerNumber(nextPlayer);

        responseMsg = new PassResponseMsg(true, newTurn, nextPlayerInt);

        broadcastMsg = new PassBroadcastMsg(playerInt, newTurn, nextPlayerInt);

        return new ResultMsgPair(responseMsg, broadcastMsg);

    }

    protected static ResultMsgPair useAtkItemResponse(Game game, Player player,
                                                      Sector position,
                                                      List<Player> kills,
                                                      List<Player> survivors) {

        ResponseMsg responseMsg;
        BroadcastMsg broadcastMsg;

        int playerInt = game.getPlayerNumber(player);

        String coordinateStr = position.getCooridnate().getOddQCol() + ":" +
                position.getCooridnate().getOddQRow();

        List<Integer> survivorsIntList = new ArrayList<>();
        Map<Integer, String> killsIntMap = new HashMap<>();

        if (survivors != null) {
            for (Player survivor : survivors) {

                int survivorInt = game.getPlayerNumber(survivor);
                survivorsIntList.add(survivorInt);

            }
        }

        if (kills != null) {
            for (Player kill : kills) {

                int killInt = game.getPlayerNumber(kill);
                String killRaceStr = kill.getCharacter().getRace().name();
                killsIntMap.put(killInt, killRaceStr);

            }
        }

        responseMsg = new UseAtkItemResponseMsg(true, coordinateStr, killsIntMap,
                survivorsIntList);
        broadcastMsg = new UseAtkItemBroadcastMsg(playerInt, coordinateStr,
                killsIntMap, survivorsIntList);

        return new ResultMsgPair(responseMsg, broadcastMsg);

    }

    protected static ResultMsgPair useTlpItemResponse(Game game, Player
            player) {

        ResponseMsg responseMsg;
        BroadcastMsg broadcastMsg;

        int playerInt = game.getPlayerNumber(player);
        Sector newPosition = game.getZone().getHumanSector();
        String coordinateStr = newPosition.getCooridnate().getOddQCol() + ":" +
                newPosition.getCooridnate().getOddQRow();

        responseMsg = new UseTlpItemResponseMsg(true, coordinateStr);
        broadcastMsg = new UseTlpItemBroadcastMsg(playerInt, coordinateStr);

        return new ResultMsgPair(responseMsg, broadcastMsg);

    }

    protected static ResultMsgPair useSdtItemResponse(Game game, Player
            player) {

        ResponseMsg responseMsg;
        BroadcastMsg broadcastMsg;

        int playerInt = game.getPlayerNumber(player);

        responseMsg = new UseSdtItemResponseMsg(true);
        broadcastMsg = new UseSdtItemBroadcastMsg(playerInt);

        return new ResultMsgPair(responseMsg, broadcastMsg);

    }

    protected static ResultMsgPair useAdrItemResponse(Game game, Player
            player) {

        ResponseMsg responseMsg;
        BroadcastMsg broadcastMsg;

        int playerInt = game.getPlayerNumber(player);

        responseMsg = new UseAdrItemResponseMsg(true);
        broadcastMsg = new UseAdrItemBroadcastMsg(playerInt);

        return new ResultMsgPair(responseMsg, broadcastMsg);

    }

    protected static ResultMsgPair useSptItemResponse(Game game, Player
            player, Sector position, List<Player> spottedPlayers) {

        ResponseMsg responseMsg;
        BroadcastMsg broadcastMsg;

        int playerInt = game.getPlayerNumber(player);

        String coordinateStr = position.getCooridnate().getOddQCol() + ":" +
                position.getCooridnate().getOddQRow();

        Map<Integer, String> spottedPlayersIntMap = new HashMap<>();

        for (Player spotted : spottedPlayers) {

            int spottedInt = game.getPlayerNumber(spotted);
            Sector spottedPos = spotted.getCharacter().getPosition();
            String spottedCoordStr = spottedPos.getCooridnate().getOddQCol() + ":" +
                    spottedPos.getCooridnate().getOddQRow();

            spottedPlayersIntMap.put(spottedInt, spottedCoordStr);

        }

        responseMsg = new UseSptItemResponseMsg(true, coordinateStr,
                spottedPlayersIntMap);
        broadcastMsg = new UseSptItemBroadcastMsg(playerInt, coordinateStr,
                spottedPlayersIntMap);

        return new ResultMsgPair(responseMsg, broadcastMsg);

    }


}
