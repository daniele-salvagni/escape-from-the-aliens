package it.polimi.ingsw.cg_2.messages.broadcast;

import java.util.HashMap;

/**
 *
 */
public class GameStartedBroadcastMsg {

    private final int gameNumber;
    private final int numberOfPlayers;
    private final int zoneName;
    private final int gameMode;

    private final HashMap<String, String> zoneSectors;

    public GameStartedBroadcastMsg(int gameNumber, int numberOfPlayers, int zoneName,
                                   int gameMode, HashMap<String, String> zoneSectors) {

        this.gameNumber = gameNumber;
        this.numberOfPlayers = numberOfPlayers;
        this.zoneName = zoneName;
        this.gameMode = gameMode;
        this.zoneSectors = zoneSectors;

    }

    public int getGameNumber() {

        return gameNumber;

    }

    public int getNumberOfPlayers() {

        return numberOfPlayers;

    }

    public int getZoneName() {

        return zoneName;

    }

    public int getGameMode() {

        return gameMode;

    }

    public HashMap<String, String> getZoneSectors() {

        return zoneSectors;

    }

}
