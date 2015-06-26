package it.polimi.ingsw.cg_2.view.gameplayer;

import it.polimi.ingsw.cg_2.messages.broadcast.*;
import it.polimi.ingsw.cg_2.messages.responses.*;
import it.polimi.ingsw.cg_2.messages.responses.actions.*;

/**
 * A visitor class for the Visitor Pattern to visit incoming messages and display them on
 * a Command Line Interface.
 */
public interface MessageVisitor {

    ////////////////////////////////////// BROADCAST \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    /**
     * Display the content of an AttackBroadcastMsg.
     *
     * @param msg the received message
     */
    void display(AttackBroadcastMsg msg);

    /**
     * Display the content of a ChatBroadcastMsg.
     *
     * @param msg the received message
     */
    void display(ChatBroadcastMsg msg);

    /**
     * Display the content of an EscapeBroadcastMsg.
     *
     * @param msg the received message
     */
    void display(EscapeBroadcastMsg msg);

    /**
     * Display the content of a NoiseBroadcastMsg.
     *
     * @param msg the received message
     */
    void display(NoiseBroadcastMsg msg);

    /**
     * Display the content of a PassBroadcastMsg.
     *
     * @param msg the received message
     */
    void display(PassBroadcastMsg msg);

    /**
     * Display the content of an UseAdrItemBroadcastMsg.
     *
     * @param msg the received message
     */
    void display(UseAdrItemBroadcastMsg msg);

    /**
     * Display the content of an UseAtkItemBroadcastMsg.
     *
     * @param msg the received message
     */
    void display(UseAtkItemBroadcastMsg msg);

    /**
     * Display the content of an UseSdtItemBroadcastMsg.
     *
     * @param msg the received message
     */
    void display(UseSdtItemBroadcastMsg msg);

    /**
     * Display the content of an UseSptItemBroadcastMsg.
     *
     * @param msg the received message
     */
    void display(UseSptItemBroadcastMsg msg);

    /**
     * Display the content of an UseTlpItemBroadcastMsg.
     *
     * @param msg the received message
     */
    void display(UseTlpItemBroadcastMsg msg);

    /**
     * Display the content of a GameStartedBroadcastMsg.
     *
     * @param msg the received message
     */
    void display(GameStartedBroadcastMsg msg);

    /**
     * Display the content of a GameFinishedBroadcastMsg.
     *
     * @param msg the received message
     */
    void display(GameFinishedBroadcastMsg msg);

    ////////////////////////////////////// RESPONSES \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    /**
     * Display the content of an AttackResponseMsg.
     *
     * @param msg the received message
     */
    void display(AttackResponseMsg msg);

    /**
     * Display the content of a DrawResponseMsg.
     *
     * @param msg the received message
     */
    void display(DrawResponseMsg msg);

    /**
     * Display the content of a EscapeResponseMsg.
     *
     * @param msg the received message
     */
    void display(EscapeResponseMsg msg);

    /**
     * Display the content of a MoveResponseMsg.
     *
     * @param msg the received message
     */
    void display(MoveResponseMsg msg);

    /**
     * Display the content of a NoiseResponseMsg.
     *
     * @param msg the received message
     */
    void display(NoiseResponseMsg msg);

    /**
     * Display the content of a PassResponseMsg.
     *
     * @param msg the received message
     */
    void display(PassResponseMsg msg);

    /**
     * Display the content of an UseAdrItemResponseMsg.
     *
     * @param msg the received message
     */
    void display(UseAdrItemResponseMsg msg);

    /**
     * Display the content of an UseAtkItemResponseMsg.
     *
     * @param msg the received message
     */
    void display(UseAtkItemResponseMsg msg);

    /**
     * Display the content of an UseSdtItemResponseMsg.
     *
     * @param msg the received message
     */
    void display(UseSdtItemResponseMsg msg);

    /**
     * Display the content of an UseSptItemResponseMsg.
     *
     * @param msg the received message
     */
    void display(UseSptItemResponseMsg msg);

    /**
     * Display the content of an UseTlpItemResponseMsg.
     *
     * @param msg the received message
     */
    void display(UseTlpItemResponseMsg msg);

    /////////////////////////////// RESPONSES (non-actions) \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    /**
     * Display the content of a ConnectionResponseMsg.
     *
     * @param msg the received message
     */
    void display(ConnectionResponseMsg msg);

    /**
     * Display the content of a InvalidRequestMsg.
     *
     * @param msg the received message
     */
    void display(InvalidRequestMsg msg);

    /**
     * Display the content of a SubscribeResponseMsg.
     *
     * @param msg the received message
     */
    void display(SubscribeResponseMsg msg);

    /**
     * Display the content of an AckResponseMsg.
     *
     * @param msg the received message
     */
    void display(AckResponseMsg msg);

    /**
     * Display the content of a PrivateDataResponseMsg.
     *
     * @param msg the received message
     */
    void display(PrivateDataResponseMsg msg);

    /**
     * Display the content of a PublicLogResponseMsg.
     *
     * @param msg the received message
     */
    void display(PublicLogResponseMsg msg);

    /**
     * Set the player number of this client.
     *
     * @param playerNum the player number of this client
     */
    void setPlayerNum(int playerNum);

    /**
     * Get the player number of this client.
     *
     * @return the player number of this client
     */
    int getPlayerNum();

}
