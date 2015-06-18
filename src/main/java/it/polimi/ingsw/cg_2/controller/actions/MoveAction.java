package it.polimi.ingsw.cg_2.controller.actions;

import it.polimi.ingsw.cg_2.controller.turn.MovedToDangerState;
import it.polimi.ingsw.cg_2.controller.turn.MovedToHatchState;
import it.polimi.ingsw.cg_2.controller.turn.MovedToSafeState;
import it.polimi.ingsw.cg_2.model.Game;
import it.polimi.ingsw.cg_2.model.deck.ItemCard;
import it.polimi.ingsw.cg_2.model.map.CloseableSector;
import it.polimi.ingsw.cg_2.model.map.CubicCoordinate;
import it.polimi.ingsw.cg_2.model.map.HexCalculator;
import it.polimi.ingsw.cg_2.model.map.Sector;
import it.polimi.ingsw.cg_2.model.player.CharacterRace;
import it.polimi.ingsw.cg_2.model.player.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This action represents a movement of the player from one sector to another,
 * it is created from a ActionFactoryVisitor when a MoveRequestMsg is received.
 */
public class MoveAction extends Action {

    private static final Logger LOG = Logger.getLogger(MoveAction.class
            .getName());

    private final Game game;
    private final Player player;
    private final Sector newSector;

    protected MoveAction(Game game, Player player, Sector newSector) {

        this.game = game;
        this.player = player;
        this.newSector = newSector;

    }

    @Override
    public boolean isValid() {

        // The grid of valid coordinates in the Zone, we make a copy since it
        // is an UnmodifiableSet.
        Set<CubicCoordinate> movementGrid = new HashSet<>(game.getZone()
                .getCoordinates());

        // Remove additional obstacles from the movement grid (Human and
        // Alien sectors)
        movementGrid.remove(game.getZone().getHumanSector().getCooridnate());
        movementGrid.remove(game.getZone().getAlienSector().getCooridnate());

        int range;

        if (player.getCharacter().getRace() == CharacterRace.HUMAN) {

            //// HUMAN: Check destination ////

            /* Humans can't land on: human sectors, alien sectors, closed
            hatch sectors. */
            if ((newSector.getType() == Sector.SectorType.HUMAN) ||
                    (newSector.getType() == Sector.SectorType.ALIEN) ||
                    ((newSector.getType() == Sector.SectorType.HATCH) &&
                            (!((CloseableSector) newSector).isOpen()))) {

                return false;

            }

            //// HUMAN: Correct destination, calculate range ////

            /* If the HUMAN player activated an Adrenaline item he can move 2
             sectors, otherwise just one. */
            range = player.getActiveItems().contains(ItemCard
                    .ItemCardType.ADRENALINE) ? 2 : 1;

        } else {

            //// ALIEN: Check destination ////

            /* Aliens can't land on: human sectors, alien sectors, every
            hatch sector. */
            if ((newSector.getType() == Sector.SectorType.HUMAN) ||
                    (newSector.getType() == Sector.SectorType.ALIEN) ||
                    (newSector.getType() == Sector.SectorType.HATCH)) {

                return false;

            }

            //// ALIEN: Correct destination, calculate range ////

            /* If the ALIEN player killed at least one human he can move 3
             sectors, otherwise just two. */
            boolean killedAnHuman = player.getNumberOfKilledHumans() > 0;
            range = killedAnHuman ? 3 : 2;

        }

        //// ALIEN & HUMAN: Check if sector is in range ////

        CubicCoordinate playerPosition = player.getCharacter().getPosition()
                .getCooridnate();

        Set<CubicCoordinate> reachableSectors = HexCalculator
                .reachableCoordinates(movementGrid, playerPosition, range);

        // Can't move to a distance of 0
        reachableSectors.remove(playerPosition);

        return reachableSectors.contains(newSector.getCooridnate());

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

        // Move the player
        player.getCharacter().setPosition(newSector);
        LOG.log(Level.INFO, "Player moved to: " + newSector.getCooridnate());

        // Create a response result for this action,
        setMessagePair(ResponseFactory.moveResponse(newSector));

        // Check the type of the destination sector
        if (newSector.getType() == Sector.SectorType.SAFE) {

            // Safe sector, nothing happens, just change state
            return MovedToSafeState.getInstance();

        } else if (newSector.getType() == Sector.SectorType.DANGEROUS) {

            // Dangerous sector, nothing happens, just change state:
            // - If no Sedatives item go to MovedToSafeState
            // - Otherwise go to MovedToDangerState

            if (player.haveActiveItem(ItemCard.ItemCardType.SEDATIVES)) {

                // Deactivate sedatives after moving
                player.deactivateItem(ItemCard.ItemCardType.SEDATIVES);
                return MovedToSafeState.getInstance();

            } else {

                return MovedToDangerState.getInstance();

            }

        } else {

            // Hatch sector, nothing happens, just change state
            return MovedToHatchState.getInstance();

        }

    }


}
