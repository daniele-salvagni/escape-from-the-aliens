package it.polimi.ingsw.cg_2.messages.broadcast;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

import java.io.Serializable;

public interface BroadcastMsg extends Serializable {

    void display(MessageVisitor visitor);

}
