package it.polimi.ingsw.cg_2.messages;

import it.polimi.ingsw.cg_2.messages.broadcast.BroadcastMsg;
import it.polimi.ingsw.cg_2.messages.responses.ResponseMsg;

/**
 * This class contains both the private response and the public broadcast
 * message pair subsequent to a certain request.
 */
public class ResultMsgPair {

    public final ResponseMsg RESPONSE;
    public final BroadcastMsg BROADCAST;

    /**
     * Creates a new ResultMsgPair containing both the private response and the
     * public broadcast message pair subsequent to a certain request.
     *
     * @param response  the private response message
     * @param broadcast the public broadcast message
     */
    public ResultMsgPair(ResponseMsg response, BroadcastMsg broadcast) {

        RESPONSE = response;
        BROADCAST = broadcast;

    }

}
