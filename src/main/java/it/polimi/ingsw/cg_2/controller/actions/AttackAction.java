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

        List<Player> playersKilled = new ArrayList<>();
        List<Player> playersSurvived = new ArrayList<>();

        Sector attackSector = player.getCharacter().getPosition();
        List<Player> playersInSector = game.getPlayersInSector(attackSector);

        // Remove the current player from the list (he cannot kill himself)
        playersInSector.remove(player);

        // Remove escaped and already dead players
        for (Player player : playersInSector) {
            if (!player.getCharacter().isAlive() || player.getCharacter()
                    .isEscaped()) {
                playersInSector.remove(player);
            }
        }

        for (Player playerToKill : playersInSector) {

            // If he does have the DEFENSE card he does survive
            if (playerToKill.haveItem(ItemCard.ItemCardType.DEFENSE)) {

                playerToKill.deactivateItem(ItemCard.ItemCardType.DEFENSE);

            } else {

                player.addKill(playerToKill.getCharacter());
                killPlayer(playerToKill);

            }

        }

        return AttackedState.getInstance();

    }

    private void killPlayer(Player playerToKill) {

        Deck<ItemCard> itemDeck = game.getItemDeck();

        playerToKill.getCharacter().setAlive(false);
        // Activated items have already been put back into the Item deck
        playerToKill.clearActiveItems();

        List<ItemCard> playerCards = playerToKill.getHeldItems();

        for (ItemCard card : playerCards) {

            player.removeItem(card.getType());
            itemDeck.discardCard(card);

        }

    }

}
