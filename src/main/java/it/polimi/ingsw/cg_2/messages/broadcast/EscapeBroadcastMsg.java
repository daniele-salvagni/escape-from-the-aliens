package it.polimi.ingsw.cg_2.messages.broadcast;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

/**
 *
 */
public class EscapeBroadcastMsg implements BroadcastMsg {

    private final int player;
    private final String cardtype;
    private final String coordinate;
    private final int newTurn;
    private final int nextPlayer;

    public EscapeBroadcastMsg(int player, String cardtype, String coordinate,
            int newTurn, int nextPlayer) {

        this.player = player;
        this.cardtype = cardtype;
        this.coordinate = coordinate;
        this.newTurn = newTurn;
        this.nextPlayer = nextPlayer;

    }

    public int getPlayer() {

        return player;

    }

    public String getCardtype() {

        return cardtype;

    }

    public String getCoordinate() {

        return coordinate;

    }

    public int getNewTurn() {

        return newTurn;

    }

    public int getNextPlayer() {

        return nextPlayer;

    }

    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
