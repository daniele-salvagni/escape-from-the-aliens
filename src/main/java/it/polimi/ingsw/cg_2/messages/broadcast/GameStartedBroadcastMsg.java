package it.polimi.ingsw.cg_2.messages.broadcast;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

import java.util.HashMap;

/**
 *
 */
public class GameStartedBroadcastMsg implements BroadcastMsg {

    private final int gameNumber;
    private final int numberOfPlayers;
    private final String zoneName;
    private final String gameMode;
    private final int playerTurn;

    private final HashMap<String, String> zoneSectors;

    public GameStartedBroadcastMsg(int gameNumber, int numberOfPlayers, String zoneName,
                                   String gameMode, int playerTurn, HashMap<String,
                                   String> zoneSectors) {

        this.gameNumber = gameNumber;
        this.numberOfPlayers = numberOfPlayers;
        this.zoneName = zoneName;
        this.gameMode = gameMode;
        this.playerTurn = playerTurn;
        this.zoneSectors = zoneSectors;

    }

    public int getGameNumber() {

        return gameNumber;

    }

    public int getNumberOfPlayers() {

        return numberOfPlayers;

    }

    public String getZoneName() {

        return zoneName;

    }

    public String getGameMode() {

        return gameMode;

    }

    public int getPlayerTurn() {

        return playerTurn;

    }

    public HashMap<String, String> getZoneSectors() {

        return zoneSectors;

    }

    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
