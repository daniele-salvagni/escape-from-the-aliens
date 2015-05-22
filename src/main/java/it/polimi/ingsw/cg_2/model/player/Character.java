package it.polimi.ingsw.cg_2.model.player;

import it.polimi.ingsw.cg_2.model.map.Sector;

/**
 * This class represent a character, it has a position, a rank and a race.
 */
public class Character {

    // The character rank should never change
    private final CharacterRank rank;
    // The character type could change (infection mode)
    private CharacterRace race;
    private Sector position;
    private boolean alive;

    /**
     * Instantiates a new character in a given position and with a given rank
     * and race.
     *
     * @param position the position relative to a {@link it.polimi.ingsw.cg_2.model.map.Zone}
     * @param rank     the rank of the character
     * @param race     the race of the character
     */
    public Character(Sector position, CharacterRank rank, CharacterRace race) {

        this.position = position;
        this.rank = rank;
        this.race = race;
        alive = true;

    }

    /**
     * Instantiates a new character in a given position and with a given rank,
     * the type will be the default one based on the rank.
     *
     * @param position the position relative to a {@link it.polimi.ingsw.cg_2.model.map.Zone}
     * @param rank     the rank of the character
     */
    public Character(Sector position, CharacterRank rank) {

        this(position, rank, rank.getDefaultType());

    }

    /**
     * Gets the position of the character relative to a {@link it.polimi.ingsw.cg_2.model.map.Zone}.
     *
     * @return the position of the character
     */
    public Sector getPosition() {

        return position;

    }

    /**
     * Sets the position of the character relative to a {@link it.polimi.ingsw.cg_2.model.map.Zone}.
     *
     * @param position the new position of the character
     */
    public void setPosition(Sector position) {

        this.position = position;

    }

    /**
     * Checks if the character is alive.
     *
     * @return true, if the character is alive
     */
    public boolean isAlive() {

        return alive;

    }

    /**
     * Sets the status of the character, true if alive or false if dead.
     *
     * @param alive the new alive
     */
    public void setAlive(boolean alive) {

        this.alive = alive;

    }

    /**
     * Gets the {@link CharacterRace} of the character.
     *
     * @return the race of the character
     */
    public CharacterRace getRace() {

        return race;

    }

    /**
     * Sets the {@link CharacterRace} of the character.
     *
     * @param race the new race of the character
     */
    public void setRace(CharacterRace race) {

        this.race = race;

    }

    /**
     * Gets the {@link CharacterRank} of the character.
     *
     * @return the rank
     */
    public CharacterRank getRank() {

        return rank;

    }

}
