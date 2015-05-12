package it.polimi.ingsw.cg_2.model.player;

import it.polimi.ingsw.cg_2.model.deck.ItemCard;
import it.polimi.ingsw.cg_2.model.map.Sector;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final Character character;
    private boolean connected;
    private boolean suspended;

    private final List<Character> kills;
    private final List<ItemCard> heldItems;
    private final List<Sector> history;

    public Player(Character character) {

        this.character = character;
        connected = true;
        suspended = false;
        kills = new ArrayList<>();
        heldItems = new ArrayList<>();
        history = new ArrayList<>();

    }

    public Character getCharacter() {

        return character;

    }

    public void moveCharacter(Sector position) {

        character.setPosition(position);
        history.add(position);

    }

    public boolean isConnected() {

        return connected;

    }

    public void setConnected(boolean connected) {

        this.connected = connected;

    }

    public boolean isSuspended() {

        return suspended;

    }

    public void setSuspended(boolean suspended) {

        this.suspended = suspended;

    }

    public List<Character> getKills() {

        return kills;

    }

    public void addKill(Character character) {

        kills.add(character);

    }

    public List<ItemCard> getHeldItems() {

        return heldItems;

    }

    public void giveItem(ItemCard item) {

        heldItems.add(item);

    }

}
