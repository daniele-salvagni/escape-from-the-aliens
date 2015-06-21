package it.polimi.ingsw.cg_2.controller.actions;

import it.polimi.ingsw.cg_2.controller.turn.AttackedState;
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
 * This action represents an attack of the player in his sector, it is created
 * from a ActionFactoryVisitor when an AttackRequestMsg is received.
 */
public class AttackAction extends Action {

    private static final Logger LOG = Logger.getLogger(AttackAction.class
            .getName());

    private final Game game;
    private final Player player;

    /**
     * Creates a new AttackAction.
     *
     * @param game   the game where to execute the action
     * @param player the player that attacks
     */
    public AttackAction(Game game, Player player) {

        this.game = game;
        this.player = player;

    }

    @Override
    public boolean isValid() {

        // ALIENS can attack, HUMANS no.
        return (player.getCharacter().getRace() == CharacterRace.ALIEN);

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

        List<Player> playersKilled = new ArrayList<>();
        List<Player> playersSurvived = new ArrayList<>();

        Sector attackSector = player.getCharacter().getPosition();
        List<Player> playersInSector = game.getPlayersInSector(attackSector);

        // Remove the current player from the list (he cannot kill himself)
        playersInSector.remove(player);

        // Remove escaped and already dead players
        for (Player playerInSector : playersInSector) {
            if (!playerInSector.getCharacter().isAlive() || playerInSector
                    .getCharacter().isEscaped()) {
                playersInSector.remove(playerInSector);
            }
        }

        Deck<ItemCard> itemDeck = game.getItemDeck();

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

                player.addKill(playerToKill.getCharacter());
                killPlayer(playerToKill);
                // Update list of killed players
                playersKilled.add(playerToKill);
                LOG.log(Level.INFO, "A player has been killed.");

            }

        }

        // Create a response result for this action,
        setMessagePair(ResponseFactory.attackResponse(game, player,
                attackSector, playersKilled, playersSurvived));

        return AttackedState.getInstance();

    }

    private void killPlayer(Player playerToKill) {

        Deck<ItemCard> itemDeck = game.getItemDeck();

        playerToKill.getCharacter().setAlive(false);
        // Activated items have already been put back into the Item deck
        playerToKill.clearActiveItems();

        List<ItemCard> playerCards = playerToKill.getHeldItems();

        for (ItemCard card : playerCards) {

            player.removeItem(card);
            itemDeck.discardCard(card);

        }

    }

}
