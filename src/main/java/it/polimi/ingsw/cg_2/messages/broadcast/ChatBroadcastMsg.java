package it.polimi.ingsw.cg_2.messages.broadcast;

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

}
