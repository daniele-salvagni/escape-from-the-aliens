package it.polimi.ingsw.cg_2.controller.actions;

import it.polimi.ingsw.cg_2.model.Game;
import it.polimi.ingsw.cg_2.model.deck.ItemCard;
import it.polimi.ingsw.cg_2.model.player.CharacterRace;
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
     * Constructor of the abstract class UseItemAction.
     *
     * @param game the game where to execute the action
     * @param player the player that executes the action
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

        // ALIENS cannot use items, HUMANS can (if they have it)
        if (player.getCharacter().getRace() == CharacterRace.ALIEN) {

            return false;

        } else { // HUMAN

            if (player.haveItem(itemType)) {
                return  true;
            } else {
                return false;
            }

        }

    }

    /**
     * Get the game where to execute the action.
     *
     * @return the game where to execute the action
     */
    protected Game getGame() {

        return game;

    }

    /**
     * Get the player that executes the action.
     *
     * @return the player that executes the action
     */
    protected Player getPlayer() {

        return player;

    }

    /**
     * Get the type of the item to use.
     *
     * @return the type of the item to use
     */
    protected ItemCard.ItemCardType getItemType() {

        return itemType;

    }

}
