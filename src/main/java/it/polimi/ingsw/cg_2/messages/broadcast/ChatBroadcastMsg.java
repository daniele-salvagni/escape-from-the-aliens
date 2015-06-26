package it.polimi.ingsw.cg_2.messages.broadcast;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

/**
 *
 */
public class ChatBroadcastMsg implements BroadcastMsg {

    private final int player;
    private final String message;

    public ChatBroadcastMsg(int player, String message) {

        this.player = player;
        this.message = message;

    }

    public int getPlayer() {

        return player;

    }

    public String getMessage() {

        return message;

    }

    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
