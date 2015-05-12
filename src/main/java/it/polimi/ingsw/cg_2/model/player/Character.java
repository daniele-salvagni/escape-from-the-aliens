/*
 * 
 */
package it.polimi.ingsw.cg_2.model.player;

import it.polimi.ingsw.cg_2.model.map.Sector;

public class Character {

    private CharacterType type;
    private CharacterRank rank;
    private Sector position;
    private boolean alive;

    /**
     * Instantiates a new character in a given position and with a given rank
     * and type.
     *
     * @param position the position relative to a {@Zone}
     * @param rank the rank of the character
     * @param type the type of the character
     */
    public Character(Sector position, CharacterRank rank, CharacterType type) {

        this.position = position;
        this.rank = rank;
        this.type = type;
        alive = true;

    }

    /**
     * Instantiates a new character in a given position and with a given rank,
     * the type will be the default one based on the rank.
     *
     * @param position the position relative to a {@Zone}
     * @param rank the rank of the character
     */
    public Character(Sector position, CharacterRank rank) {

        this(position, rank, rank.getDefaultType());

    }

    /**
     * Gets the position of the character relative to a {@Zone}.
     *
     * @return the position of the character
     */
    public Sector getPosition() {

        return position;

    }

    /**
     * Sets the position of the character relative to a {@Zone}.
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
     * Gets the type of the character.
     *
     * @return the type of the character
     */
    public CharacterType getType() {

        return type;

    }

    /**
     * Sets the type of the character.
     *
     * @param type the new type of the character
     */
    public void setType(CharacterType type) {

        this.type = type;

    }

    /**
     * Gets the rank of the character.
     *
     * @return the rank
     */
    public CharacterRank getRank() {

        return rank;

    }

}
