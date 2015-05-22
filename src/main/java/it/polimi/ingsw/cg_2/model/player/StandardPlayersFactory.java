package it.polimi.ingsw.cg_2.model.player;

import it.polimi.ingsw.cg_2.model.map.Sector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This concrete factory implementation of {@link PlayersFactory} creates a
 * List
 * of players, half with an alien character and half with an human one, if the
 * number of players is odd there will be one more aliens than humans. The
 * characters will be placed in the appropriate spawn sector (alien or human).
 * <p/>
 * The list of players is <b>Shuffled</b>.
 */
public class StandardPlayersFactory implements PlayersFactory {

    Random rand;

    // The human and alien sectors relative to a Zone
    Sector humanSector;
    Sector alienSector;

    // Separate all the possible humans and alien characters.
    List<CharacterRank> humanRanks;
    List<CharacterRank> alienRanks;

    protected StandardPlayersFactory() {

        rand = new Random();

        humanRanks = new ArrayList<>();
        alienRanks = new ArrayList<>();

    }

    public List<Player> createPlayers(int number, Sector humanSector, Sector
            alienSector) {

        if (number > 8 || number < 1) {
            throw new IllegalArgumentException("The maximum allowed number of" +
                    " players is 8.");
        }

        this.humanSector = humanSector;
        this.alienSector = alienSector;

        // Populate humanRanks and alienRanks by looping trough the enum, we
        // will use them to keep track of the already drawn character ranks.
        for (CharacterRank rank : CharacterRank.values()) {
            if (rank.getDefaultType() == CharacterRace.HUMAN) {
                humanRanks.add(rank);
            } else if (rank.getDefaultType() == CharacterRace.ALIEN) {
                alienRanks.add(rank);
            }
        }

        // Initialize a list that will contain the players to return.
        List<Player> players = new ArrayList<>();

        /* If the number of players is odd the aliens will be one more than
        the humans, so we start by picking an alien and than we keep
        alternating between keeping an human and an alien. */
        for (int i = 0; i < number; i++) {

            CharacterRank rank;

            if (i % 2 == 0) {

                // Select a random Alien Rank
                rank = alienRanks.get(rand.nextInt(alienRanks.size()));
                // We already draw that alien, so we remove it form the list
                alienRanks.remove(rank);

            } else {

                // Select a random Human Rank
                rank = humanRanks.get(rand.nextInt(humanRanks.size()));
                // We already draw that human, so we remove it form the list
                humanRanks.remove(rank);

            }

            players.add(createPlayer(rank));

        }

        // Shuffle the list of players before returning it.
        Collections.shuffle(players);

        return players;

    }

    /**
     * Creates a new {@link Player} with a a Character of given rank in the
     * appropriate sector, human or alien.
     *
     * @param rank the rank of the Player's Character
     * @return a new Player
     */
    private Player createPlayer(CharacterRank rank) {

        Character character;

        if (rank.getDefaultType() == CharacterRace.HUMAN) {
            character = new Character(humanSector, rank);
        } else {
            character = new Character(alienSector, rank);
        }

        return new Player(character);

    }


}
