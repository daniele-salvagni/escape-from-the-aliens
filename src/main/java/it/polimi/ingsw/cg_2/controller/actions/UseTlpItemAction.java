package it.polimi.ingsw.cg_2.controller.actions;

import it.polimi.ingsw.cg_2.model.Game;
import it.polimi.ingsw.cg_2.model.deck.Deck;
import it.polimi.ingsw.cg_2.model.deck.ItemCard;
import it.polimi.ingsw.cg_2.model.player.Player;

import java.util.logging.Logger;

/**
 * This action represents a player that uses a teleport item, it is created from
 * a ActionFactoryVisitor when an UseTlpItemRequestMsg is received.
 */
public class UseTlpItemAction extends UseItemAction {

    private static final Logger LOG = Logger.getLogger(UseTlpItemAction.class
            .getName());

    /**
     * Create a new UseTlpActionMsg
     *
     * @param game the game where to execute the action
     * @param player the player that executes the action
     */
    public UseTlpItemAction(Game game, Player player) {

        super(game, player, ItemCard.ItemCardType.TELEPORT);

    }

    @Override
    public Object execute() {

        /* These situations could happen only by programming errors, we don't
         want to recover from that. */
        if (super.hasBeenExecuted()) {
            throw new AssertionError("An action should be executed only once");
        } else if (!isValid()) {
            throw new AssertionError("An action should be executed only if " +
                    "valid");
        }

        // Set this action as executed, so it cannot be executed another time
        super.setExecuted();

        // Remove the item from the player and put it back in the deck
        Deck<ItemCard> itemDeck = getGame().getItemDeck();
        ItemCard item = getPlayer().removeItem(getItemType());
        itemDeck.discardCard(item);

        getPlayer().getCharacter().setPosition(getGame().getZone()
                .getHumanSector());

        // Create a response result for this action,
        setMessagePair(ResponseFactory.useTlpItemResponse(getGame(),
                getPlayer()));

        return null;

    }

}
