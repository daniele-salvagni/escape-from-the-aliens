package it.polimi.ingsw.cg_2.controller.actions;

import it.polimi.ingsw.cg_2.controller.turn.DrawnDeceptionAndItemState;
import it.polimi.ingsw.cg_2.controller.turn.DrawnDeceptionState;
import it.polimi.ingsw.cg_2.controller.turn.MadeNoiseState;
import it.polimi.ingsw.cg_2.model.Game;
import it.polimi.ingsw.cg_2.model.deck.Deck;
import it.polimi.ingsw.cg_2.model.deck.ItemCard;
import it.polimi.ingsw.cg_2.model.deck.SectorCard;
import it.polimi.ingsw.cg_2.model.player.Player;

import java.util.logging.Logger;

/**
 * This action represents a player drawing a card, it is created from a
 * ActionFactoryVisitor when a DrawRequestMsg is received.
 */
public class DrawAction extends Action {

    private static final Logger LOG = Logger.getLogger(DrawAction.class
            .getName());

    private final Game game;
    private final Player player;

    public DrawAction(Game game, Player player) {

        this.game = game;
        this.player = player;

    }

    @Override
    public boolean isValid() {

        // This action is always valid
        return true;

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

        Deck<SectorCard> sectorDeck = game.getSectorDeck();
        Deck<ItemCard> itemDeck = game.getItemDeck();

        // Draw a Sector Card
        SectorCard sectorCard = sectorDeck.drawCard();
        ItemCard itemCard;

        // Check if the sector card contains an item
        if (sectorCard.containsItem()) {
            // Draw an item card and give it to the player
            itemCard = itemDeck.drawCard();
            player.giveItem(itemCard);
        } else {
            itemCard = null;
        }

        // Create a response result for this action,
        setMessagePair(ResponseFactory.drawResponse(game, player,
                sectorCard, itemCard));

        if (sectorCard.getType() != SectorCard.SectorCardType.DECEPTION) {

            if (itemCard != null) {

                return DrawnDeceptionAndItemState.getInstance();

            } else {

                return DrawnDeceptionState.getInstance();

            }

        } else {

            return MadeNoiseState.getInstance();

        }

    }
}
