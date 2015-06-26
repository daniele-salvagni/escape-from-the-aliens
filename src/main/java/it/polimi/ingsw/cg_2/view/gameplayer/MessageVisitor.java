package it.polimi.ingsw.cg_2.view.gameplayer;

import it.polimi.ingsw.cg_2.messages.broadcast.*;
import it.polimi.ingsw.cg_2.messages.responses.*;
import it.polimi.ingsw.cg_2.messages.responses.actions.*;

/**
 *
 */
public interface MessageVisitor {

    void display(AttackBroadcastMsg msg);

    void display(ChatBroadcastMsg msg);

    void display(EscapeBroadcastMsg msg);

    void display(NoiseBroadcastMsg msg);

    void display(PassBroadcastMsg msg);

    void display(UseAdrItemBroadcastMsg msg);

    void display(UseAtkItemBroadcastMsg msg);

    void display(UseSdtItemBroadcastMsg msg);

    void display(UseSptItemBroadcastMsg msg);

    void display(UseTlpItemBroadcastMsg msg);

    void display(GameStartedBroadcastMsg msg);


    void display(AttackResponseMsg msg);

    void display(DrawResponseMsg msg);

    void display(EscapeResponseMsg msg);

    void display(MoveResponseMsg msg);

    void display(NoiseResponseMsg msg);

    void display(PassResponseMsg msg);

    void display(UseAdrItemResponseMsg msg);

    void display(UseAtkItemResponseMsg msg);

    void display(UseSdtItemResponseMsg msg);

    void display(UseSptItemResponseMsg msg);

    void display(UseTlpItemResponseMsg msg);


    void display(ConnectionResponseMsg msg);

    void display(InvalidRequestMsg msg);

    void display(SubscribeResponseMsg msg);

    void display(AckResponseMsg msg);

    void display(PrivateDataResponseMsg msg);

    void display(PublicLogResponseMsg msg);


    void setPlayerNum(int playerNum);

    int getPlayerNum();

}
