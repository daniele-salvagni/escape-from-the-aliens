package it.polimi.ingsw.cg_2.controller.actions;

import it.polimi.ingsw.cg_2.controller.turn.TurnStartedState;
import it.polimi.ingsw.cg_2.model.Game;
import it.polimi.ingsw.cg_2.model.player.Player;

import java.util.List;
import java.util.logging.Logger;

/**
 * This action represents a player passing the turn escape, it is created from a
 * ActionFactoryVisitor when a PassRequestMsg is received.
 */
public class PassAction extends Action {

    private static final Logger LOG = Logger.getLogger(PassAction.class
            .getName());

    private final Game game;
    private final Player player;

    /**
     * Create a new PassAction.
     *
     * @param game the game where to execute the action
     * @param player the player that executes the action
     */
    public PassAction(Game game, Player player) {

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

        // Set this action as executed, so it cannot be executed another time
        super.setExecuted();

        Player nextPlayer = pass(player);

        game.setCurrentPlayer(nextPlayer);

        // Create a response result for this action,
        setMessagePair(ResponseFactory.passResponse(game, player,
                game.getTurnNumber(), nextPlayer));

        return TurnStartedState.getInstance();

    }

    /**
     * Set a player as escaped, put back his items in the deck, deactivate the
     * activated items and find the following player.
     *
     * @param player the player that does pass the turn
     * @return the next player
     */
    private Player pass(Player player) {

        player.clearActiveItems();

        int playerNumber = game.getPlayerNumber(player);
        List<Player> players = game.getPlayers();

        Player nextPlayer = player;

        // Find the next player
        for (int i = 1; i < players.size(); i++) {
            if (!player.isSuspended() && player.isConnected() && !player
                    .getCharacter().isEscaped() && player.getCharacter()
                    .isAlive()) {
                nextPlayer = players.get((i + playerNumber) % players.size());
            }
        }

        // Increase turn number
        game.setTurnNumber(game.getTurnNumber() + 1);

        return nextPlayer;

    }

}
