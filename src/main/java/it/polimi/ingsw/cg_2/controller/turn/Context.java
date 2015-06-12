package it.polimi.ingsw.cg_2.controller.turn;

import it.polimi.ingsw.cg_2.model.deck.ItemCard.ItemCardType;
import it.polimi.ingsw.cg_2.model.deck.SectorCard.SectorCardType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class keeps track of the context of a certain turn, such as the active
 * items (powerups) or the sector card found by the player (silence, noise,
 * deception), if a client landed in a deception .
 */
public class Context {

    private List<ItemCardType> activeItems;
    private SectorCardType sectorCard;

    public Context() {

        activeItems = new ArrayList<>();
        sectorCard = null;

    }

    public List<ItemCardType> getActiveItems() {

        return Collections.unmodifiableList(activeItems);

    }

    public SectorCardType getSectorCard() {

        return sectorCard;

    }

    public boolean isActive(ItemCardType itemCard) {

        return activeItems.contains(itemCard);

    }

    public void activateCard(ItemCardType itemCard) {

        activeItems.add(itemCard);

    }

    public void setSectorCard(SectorCardType sectorCard) {

        this.sectorCard = sectorCard;

    }

}
