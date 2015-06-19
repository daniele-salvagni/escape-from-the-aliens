package it.polimi.ingsw.cg_2.controller.actions;

import it.polimi.ingsw.cg_2.model.Game;
import it.polimi.ingsw.cg_2.model.player.CharacterRace;
import it.polimi.ingsw.cg_2.model.player.Player;

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

        return null;

    }

}
