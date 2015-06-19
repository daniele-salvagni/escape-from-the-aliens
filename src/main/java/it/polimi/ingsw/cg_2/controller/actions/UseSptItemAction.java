package it.polimi.ingsw.cg_2.controller.actions;

import it.polimi.ingsw.cg_2.model.Game;
import it.polimi.ingsw.cg_2.model.deck.Deck;
import it.polimi.ingsw.cg_2.model.deck.ItemCard;
import it.polimi.ingsw.cg_2.model.map.CubicCoordinate;
import it.polimi.ingsw.cg_2.model.map.HexCalculator;
import it.polimi.ingsw.cg_2.model.map.Sector;
import it.polimi.ingsw.cg_2.model.player.Player;

import java.util.*;
import java.util.logging.Logger;

/**
 * This action represents a player that uses a spotlight item, it is created
 * from a ActionFactoryVisitor when an UseSptItemRequestMsg is received.
 */
public class UseSptItemAction extends UseItemAction {

    private static final Logger LOG = Logger.getLogger(UseSptItemAction.class
            .getName());

    private final Sector position;

    /**
     * Create a new UseSptItemAction.
     *
     * @param game     the game where to execute the action
     * @param player   the player that executes the action
     * @param position the sector where to use the spotlight item
     */
    public UseSptItemAction(Game game, Player player, Sector position) {

        super(game, player, ItemCard.ItemCardType.SPOTLIGHT);
        this.position = position;

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

        List<Player> spottedPlayers = new ArrayList<>();

        // The grid of valid coordinates in the Zone, we make a copy since it
        // is an UnmodifiableSet.
        Set<CubicCoordinate> movementGrid = getGame().getZone()
                .getCoordinates();

        Set<CubicCoordinate> adjacentSectors = HexCalculator
                .reachableCoordinates(movementGrid, position.getCooridnate(),
                        1);

        for (CubicCoordinate coordinate : adjacentSectors) {

            Sector sector = getGame().getZone().getSector(coordinate);
            if (sector != null) {
                spottedPlayers.addAll(getGame().getPlayersInSector(sector));
            }

        }

        // Create a response result for this action,
        setMessagePair(ResponseFactory.useSptItemResponse(getGame(),
                getPlayer(), position, spottedPlayers));

        // Does not change the state
        return null;

    }

}
