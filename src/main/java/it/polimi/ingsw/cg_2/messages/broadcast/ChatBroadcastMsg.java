package it.polimi.ingsw.cg_2.messages.broadcast;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

/**
 * A message to broadcast a text chat message.
 */
public class ChatBroadcastMsg implements BroadcastMsg {

    private final int player;
    private final String message;

    /**
     * Create a new ChatBroadcastMessage.
     *
     * @param player the number of the player sending the message
     * @param message the text of the message
     */
    public ChatBroadcastMsg(int player, String message) {

        this.player = player;
        this.message = message;

    }

    /**
     * Get the number of the player sending the message.
     *
     * @return the number of the player sending the message
     */
    public int getPlayer() {

        return player;

    }

    /**
     * Get the text of the message.
     *
     * @return the text of the message
     */
    public String getMessage() {

        return message;

    }

    @Override
    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
