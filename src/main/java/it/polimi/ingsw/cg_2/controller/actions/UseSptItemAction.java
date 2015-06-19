package it.polimi.ingsw.cg_2.controller.actions;

import it.polimi.ingsw.cg_2.model.Game;
import it.polimi.ingsw.cg_2.model.deck.ItemCard;
import it.polimi.ingsw.cg_2.model.player.Player;

import java.util.logging.Logger;

/**
 *
 */
public class UseSptItemAction extends UseItemAction {

    private static final Logger LOG = Logger.getLogger(UseSptItemAction.class
            .getName());

    /**
     * Create a new UseSptItemAction.
     *
     * @param game the game where to execute the action
     * @param player the player that executes the action
     */
    public UseSptItemAction(Game game, Player player) {

        super(game, player, ItemCard.ItemCardType.ADRENALINE);

    }

    @Override
    public Object execute() {

        return null;

    }

}
