package it.polimi.ingsw.cg_2.messages.broadcast;

import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

import java.util.HashMap;

/**
 * A message to broadcast the start of a new game.
 */
public class GameStartedBroadcastMsg implements BroadcastMsg {

    private final int gameNumber;
    private final int numberOfPlayers;
    private final String zoneName;
    private final String gameMode;
    private final int playerTurn;

    private final HashMap<String, String> zoneSectors;

    /**
     * Create a new GameStartedBroadcastMsg.
     *
     * @param gameNumber the ID of the game
     * @param numberOfPlayers the number of players in the game
     * @param zoneName the name of the zone of the game
     * @param gameMode the name of the gamemode
     * @param playerTurn the player starting the turn
     * @param zoneSectors the coordinates of all the sectors of the zone and their type
     */
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

    /**
     * Get the ID of the game
     *
     * @return the ID of the game
     */
    public int getGameNumber() {

        return gameNumber;

    }

    /**
     * Get the number of players in the game.
     *
     * @return the number of players in the game
     */
    public int getNumberOfPlayers() {

        return numberOfPlayers;

    }

    /**
     * Get the name of the zone of the game.
     *
     * @return the name of the zone of the game
     */
    public String getZoneName() {

        return zoneName;

    }

    /**
     * Get the name of the gamemode.
     *
     * @return the name of the gamemode
     */
    public String getGameMode() {

        return gameMode;

    }

    /**
     * Get the player starting the turn.
     *
     * @return the player starting the turn
     */
    public int getPlayerTurn() {

        return playerTurn;

    }

    /**
     * Get the coordinates of all the sectors of the zone and their type.
     *
     * @return the coordinates of all the sectors of the zone and their type
     */
    public HashMap<String, String> getZoneSectors() {

        return zoneSectors;

    }

    @Override
    public void display(MessageVisitor visitor) {

        visitor.display(this);

    }

}
