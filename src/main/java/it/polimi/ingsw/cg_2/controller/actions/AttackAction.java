package it.polimi.ingsw.cg_2.controller.actions;

import it.polimi.ingsw.cg_2.model.Game;
import it.polimi.ingsw.cg_2.model.player.CharacterRace;
import it.polimi.ingsw.cg_2.model.player.Player;

import java.util.logging.Logger;

/**
 *
 */
public class AttackAction extends Action {

    private static final Logger LOG = Logger.getLogger(AttackAction.class
            .getName());

    private final Game game;
    private final Player player;

    public AttackAction(Game game, Player player) {

        this.game = game;
        this.player = player;

    }

    @Override
    public boolean isValid() {

        // ALIENS can attack, HUMANS no.
        if (player.getCharacter().getRace() == CharacterRace.ALIEN) {

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
