package it.polimi.ingsw.cg_2.controller.actions;

import it.polimi.ingsw.cg_2.model.Game;
import it.polimi.ingsw.cg_2.model.deck.Deck;
import it.polimi.ingsw.cg_2.model.deck.ItemCard;
import it.polimi.ingsw.cg_2.model.map.Sector;
import it.polimi.ingsw.cg_2.model.player.CharacterRace;
import it.polimi.ingsw.cg_2.model.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This action represents an attack of the player in his sector (using an
 * item), it is created from a ActionFactoryVisitor when an
 * UseAtkItemRequestMsg is received.
 */
public class UseAtkItemAction extends UseItemAction {

    private static final Logger LOG = Logger.getLogger(UseAtkItemAction.class
            .getName());

    public UseAtkItemAction(Game game, Player player, ItemCard.ItemCardType
            itemType) {

        super(game, player, itemType);

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

        List<Player> playersKilled = new ArrayList<>();
        List<Player> playersSurvived = new ArrayList<>();

        Sector attackSector = getPlayer().getCharacter().getPosition();
        List<Player> playersInSector = getGame().getPlayersInSector
                (attackSector);

        // Remove the current player from the list (he cannot kill himself)
        playersInSector.remove(getPlayer());

        // Remove escaped and already dead players
        for (Player player : playersInSector) {
            if (!player.getCharacter().isAlive() || player.getCharacter()
                    .isEscaped()) {
                playersInSector.remove(player);
            }
        }

        for (Player playerToKill : playersInSector) {

            // If he does have the DEFENSE card (and he is human) he does
            // survive
            if (playerToKill.haveItem(ItemCard.ItemCardType.DEFENSE) &&
                    (playerToKill.getCharacter().getRace() == CharacterRace
                            .HUMAN)) {

                // Remove the DEFENSE card form the player and put it back in
                // the deck
                ItemCard defenseCard = playerToKill.removeItem(ItemCard
                        .ItemCardType.DEFENSE);
                itemDeck.discardCard(defenseCard);

                // Update list of survived players
                playersSurvived.add(playerToKill);
                LOG.log(Level.INFO, "A player used DEFENSE.");

            } else {

                getPlayer().addKill(playerToKill.getCharacter());
                killPlayer(playerToKill);
                // Update list of killed players
                playersKilled.add(playerToKill);
                LOG.log(Level.INFO, "A player has been killed.");

            }

        }

        // Create a response result for this action,
        setMessagePair(ResponseFactory.useAtkItemResponse(getGame(),
                getPlayer(), attackSector, playersKilled, playersSurvived));

        return null;

    }

    private void killPlayer(Player playerToKill) {

        Deck<ItemCard> itemDeck = getGame().getItemDeck();

        playerToKill.getCharacter().setAlive(false);
        // Activated items have already been put back into the Item deck
        playerToKill.clearActiveItems();

        List<ItemCard> playerCards = playerToKill.getHeldItems();

        for (ItemCard card : playerCards) {

            getPlayer().removeItem(card);
            itemDeck.discardCard(card);

        }

    }

}
