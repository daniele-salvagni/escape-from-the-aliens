package it.polimi.ingsw.cg_2.controller.actions;

import it.polimi.ingsw.cg_2.controller.turn.MadeNoiseState;
import it.polimi.ingsw.cg_2.model.Game;
import it.polimi.ingsw.cg_2.model.map.Sector;
import it.polimi.ingsw.cg_2.model.noise.Noise;
import it.polimi.ingsw.cg_2.model.player.Player;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This action represents a noise made by the player in a certain sector, it is
 * created from a ActionFactoryVisitor when a NoiseRequestMsg is received.
 */
public class NoiseAction extends Action {

    private static final Logger LOG = Logger.getLogger(NoiseAction.class
            .getName());

    private final Game game;
    private final Player player;
    private final Sector position;
    private final boolean item;

    /**
     * Creates a new NoiseAction.
     *
     * @param game     the game where to perform the action
     * @param player   the player that performs the action
     * @param position the sector where the noise will happen
     * @param item     true if the player also found an item card
     */
    public NoiseAction(Game game, Player player, Sector position, boolean
            item) {

        this.game = game;
        this.player = player;
        this.position = position;
        this.item = item;

    }

    @Override
    public boolean isValid() {

        // This action is always valid (the existence of the Sector is
        // already checked by the Action Factory)
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

        // Create a response result for this action,
        setMessagePair(ResponseFactory.noiseResponse(game, player, position,
                item));

        // Add the Noise to the list of noises
        Noise noise = new Noise(player, position, game.getTurnNumber());
        game.addNoise(noise);

        LOG.log(Level.INFO, "Player made noise in " + position.getCooridnate());

        return MadeNoiseState.getInstance();

    }

}
