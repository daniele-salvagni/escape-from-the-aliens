package it.polimi.ingsw.cg_2.controller.actions;

import it.polimi.ingsw.cg_2.model.Game;
import it.polimi.ingsw.cg_2.model.deck.Deck;
import it.polimi.ingsw.cg_2.model.deck.ItemCard;
import it.polimi.ingsw.cg_2.model.player.Player;

import java.util.logging.Logger;

/**
 * This action represents a player that uses an adrenaline item, it is created
 * from a ActionFactoryVisitor when an UseAdrItemRequestMsg is received.
 */
public class UseAdrItemAction extends UseItemAction {

    private static final Logger LOG = Logger.getLogger(UseAdrItemAction.class
            .getName());

    /**
     * Create a new UseAdrItemAction.
     *
     * @param game the game where to execute the action
     * @param player the player that executes the action
     */
    public UseAdrItemAction(Game game, Player player) {

        super(game, player, ItemCard.ItemCardType.ADRENALINE);

    }

    @Override
    public Object execute() {

        // Set this action as executed, so it cannot be executed another time
        super.setExecuted();

        // Remove the item from the player and put it back in the deck
        Deck<ItemCard> itemDeck = getGame().getItemDeck();
        ItemCard item = getPlayer().removeItem(getItemType());
        itemDeck.discardCard(item);

        // Activate an ADRENALINE card for the player in this turn
        getPlayer().activateItem(getItemType());

        // Create a response result for this action,
        setMessagePair(ResponseFactory.useAdrItemResponse(getGame(),
                getPlayer()));

        // Does not change the state
        return null;

    }

}
