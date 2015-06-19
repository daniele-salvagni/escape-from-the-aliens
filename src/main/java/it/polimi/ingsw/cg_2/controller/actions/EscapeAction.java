package it.polimi.ingsw.cg_2.controller.actions;

import it.polimi.ingsw.cg_2.controller.turn.TriedToEscapeState;
import it.polimi.ingsw.cg_2.controller.turn.TurnStartedState;
import it.polimi.ingsw.cg_2.model.Game;
import it.polimi.ingsw.cg_2.model.deck.Deck;
import it.polimi.ingsw.cg_2.model.deck.HatchCard;
import it.polimi.ingsw.cg_2.model.deck.ItemCard;
import it.polimi.ingsw.cg_2.model.player.CharacterRace;
import it.polimi.ingsw.cg_2.model.player.Player;

import java.util.List;
import java.util.logging.Logger;

/**
 * This action represents a player trying to escape, it is created from a
 * ActionFactoryVisitor when a EscapeRequestMsg is received.
 */
public class EscapeAction extends Action {

    private static final Logger LOG = Logger.getLogger(EscapeAction.class
            .getName());

    private final Game game;
    private final Player player;

    /**
     * Creates a new DrawAction.
     *
     * @param game   the game where to perform the action
     * @param player the player that performs the action
     */
    public EscapeAction(Game game, Player player) {

        this.game = game;
        this.player = player;

    }

    @Override
    public boolean isValid() {

        /*
         * Only humans can escape, but the way an alien will not be allowed to
         * execute this action also because he cannot be in the
         * "MovedToHatchState" (thw only state from which this action is valid).
         */
        if (player.getCharacter().getRace() == CharacterRace.HUMAN) {
            return true;
        } else {
            return false;
        }

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

        Deck<HatchCard> hatchDeck = game.getHatchDeck();

        // Draw a Sector Card, store it...
        HatchCard hatchCard = hatchDeck.drawCard();
        // ... and put it back in the deck
        hatchDeck.discardCard(hatchCard);

        if (hatchCard.getType() == HatchCard.HatchCardType.GREEN) {

            // Player escaped and turn goes to the next player

            Player nextPlayer = escape(player);
            game.setCurrentPlayer(nextPlayer);

            // Create a response result for this action,
            setMessagePair(ResponseFactory.escapeResponse(game, player,
                    hatchCard, nextPlayer));

            return TurnStartedState.getInstance();

        } else { // RED

            // Create a response result for this action,
            setMessagePair(ResponseFactory.escapeResponse(game, player,
                    hatchCard, null));

            return TriedToEscapeState.getInstance();

        }


    }

    /**
     * Set a player as escaped, put back his items in the deck, deactivate the
     * activated items and find the following player.
     *
     * @param player the player that escapes
     * @return the next player
     */
    private Player escape(Player player) {

        Deck<ItemCard> itemDeck = game.getItemDeck();
        List<ItemCard> playerItems = player.getHeldItems();

        player.getCharacter().setEscaped(true);

        for (ItemCard item : playerItems) {

            itemDeck.discardCard(item);
            player.removeItem(item);

        }

        player.clearActiveItems();

        int playerNumber = game.getPlayerNumber(player);
        List<Player> players = game.getPlayers();

        Player nextPlayer = player;

        // Find the next player
        for (int i = 1; i < players.size(); i++) {
            if (!player.isSuspended() && player.isConnected() && !player
                    .getCharacter().isEscaped() && player.getCharacter()
                    .isAlive()) {
                players.get((i + playerNumber) % players.size());
            }
        }

        // It could return the same player, but in this case the game will
        // end immediately after this.
        return nextPlayer;

    }

}
