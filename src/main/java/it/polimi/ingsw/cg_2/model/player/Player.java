package it.polimi.ingsw.cg_2.model.player;

import it.polimi.ingsw.cg_2.model.deck.ItemCard;
import it.polimi.ingsw.cg_2.model.map.Sector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a Player of a game which uses a {@link Character}.
 *
 */
public class Player {

    private final Character character;
    private boolean connected;
    private boolean suspended;

    private final List<Character> kills;
    private final List<ItemCard> heldItems;
    private final List<Sector> history;

    /**
     * Instantiates a new player and give him a character to play with.
     *
     * @param character the character to give to the player
     */
    public Player(Character character) {

        this.character = character;
        connected = true;
        suspended = false;
        kills = new ArrayList<>();
        heldItems = new ArrayList<>();
        history = new ArrayList<>();

    }

    /**
     * Gets the character used by the player.
     *
     * @return the character
     */
    public Character getCharacter() {

        return character;

    }

    /**
     * Moves the character used by the player in a new {@link Sector}, the move
     * will be stored in a movement history.
     *
     * @param position the new position of the character
     */
    public void moveCharacter(Sector position) {

        character.setPosition(position);
        // Add the new position to the movement history
        history.add(position);

    }

    /**
     * Gets the movement history of the player.
     *
     * @return the movement history
     */
    public List<Sector> getMovementHistory() {

        /* Return an unmodifiable view of the list to reduce mutability. */
        return Collections.unmodifiableList(history);

    }

    /**
     * Checks if the player is connected.
     *
     * @return true, if the player is connected
     */
    public boolean isConnected() {

        return connected;

    }

    /**
     * Sets the connected status of the player.
     *
     * @param connected true if connected, false otherwise
     */
    public void setConnected(boolean connected) {

        this.connected = connected;

    }

    /**
     * Checks if the player has been suspended.
     *
     * @return true, if the player is suspended
     */
    public boolean isSuspended() {

        return suspended;

    }

    /**
     * Sets the suspended status of the player.
     *
     * @param suspended true to suspend the player
     */
    public void setSuspended(boolean suspended) {

        this.suspended = suspended;

    }

    /**
     * Gets the {@link Character}s killed made by the player.
     *
     * @return the kills made by the player
     */
    public List<Character> getKills() {

        /* Return an unmodifiable view of the list to reduce mutability. */
        return Collections.unmodifiableList(kills);

    }

    /**
     * Adds a new {@link Character} to the player kills.
     *
     * @param character the {@link Character} killed by the player
     */
    public void addKill(Character character) {

        kills.add(character);

    }

    /**
     * Gets the {@link ItemCard}s held by the player. An unmodifiable view of
     * the list is reurned.
     *
     * @return the held items (unmodificabeList)
     * 
     * @see Collections#unmodifiableList(List)
     */
    public List<ItemCard> getHeldItems() {

        /* Return an unmodifiable view of the list to reduce mutability. */
        return Collections.unmodifiableList(heldItems);

    }

    /**
     * Give an {@link ItemCard} to the player.
     *
     * @param item the new item
     */
    public void giveItem(ItemCard item) {

        heldItems.add(item);

    }

    /**
     * Removes the item from the items held by the player.
     *
     * @param item the item to remove from the player
     * @return true, the player contained that item
     */
    public boolean removeItem(ItemCard item) {

        return heldItems.remove(item);

    }

}
