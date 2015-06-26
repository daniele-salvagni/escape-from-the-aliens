package it.polimi.ingsw.cg_2.messages.broadcast;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

/**
 * A message to broadcast a Noise made by a player.
 */
public class NoiseBroadcastMsg implements BroadcastMsg {

    private final int player;
    private final String cardType;
    private final String coordinate;
    private final boolean item;

    /**
     * Create a new NoiseBroadcastMsg.
     *
     * @param player the number of the player that made the noise
     * @param cardType the type of noise
     * @param coordinate the coordinate of the noise
     * @param item true, if the player also found an item
     */
    public NoiseBroadcastMsg(int player, String cardType, String coordinate,
            boolean item) {

        this.player = player;
        this.cardType = cardType;
        this.coordinate = coordinate;
        this.item = item;

    }

    /**
     * Get the number of the player that made the noise.
     *
     * @return the number of the player that made the noise
     */
    public int getPlayer() {

        return player;

    }

    /**
     * Get the type of noise.
     *
     * @return the type of noise
     */
    public String getCardType() {

        return cardType;

    }

    /**
     * Get the coordinate of the noise.
     *
     * @return the coordinate of the noise
     */
    public String getCoordinate() {

        return coordinate;

    }

    /**
     * Check if the player also found an item.
     *
     * @return true, if the player also found an item
     */
    public boolean isItem() {

        return item;

    }

    @Override
    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
