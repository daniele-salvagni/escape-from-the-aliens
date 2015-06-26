package it.polimi.ingsw.cg_2.messages.broadcast;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

/**
 *
 */
public class NoiseBroadcastMsg implements BroadcastMsg {

    private final int player;
    private final String cardType;
    private final String coordinate;
    private final boolean item;

    public NoiseBroadcastMsg(int player, String cardType, String coordinate,
            boolean item) {

        this.player = player;
        this.cardType = cardType;
        this.coordinate = coordinate;
        this.item = item;

    }

    public int getPlayer() {

        return player;

    }

    public String getCardType() {

        return cardType;

    }

    public String getCoordinate() {

        return coordinate;

    }

    public boolean isItem() {

        return item;

    }

    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
