package it.polimi.ingsw.cg_2.controller.actions;

import it.polimi.ingsw.cg_2.model.Game;
import it.polimi.ingsw.cg_2.model.deck.ItemCard;
import it.polimi.ingsw.cg_2.model.player.Player;

/**
 * This is a state of the state machine that manages a game. It represents
 * the state where a Player uses an Item.
 */
public abstract class UseItemAction extends Action {

    private final Game game;
    private final Player player;
    private final ItemCard.ItemCardType itemType;

    /**
     * Constructor for UseItemAction.
     *
     * @param itemType the type of the item to use
     */
    protected UseItemAction(Game game, Player player, ItemCard.ItemCardType
            itemType) {

        if (itemType == ItemCard.ItemCardType.DEFENSE) {
            throw new IllegalArgumentException("Defense is not an usable item" +
                    ".");
        }

        this.game = game;
        this.player = player;
        this.itemType = itemType;

    }

    @Override
    public boolean isValid() {

        // Using the majority of items is aways valid, if not true we will
        // override this method in subclasses
        return true;

    }

    public Game getGame() {

        return game;

    }

    public Player getPlayer() {

        return player;

    }

    public ItemCard.ItemCardType getItemType() {

        return itemType;

    }

}
