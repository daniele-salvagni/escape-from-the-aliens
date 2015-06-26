package it.polimi.ingsw.cg_2.view.gameplayer.cli;

import it.polimi.ingsw.cg_2.messages.broadcast.*;
import it.polimi.ingsw.cg_2.messages.responses.*;
import it.polimi.ingsw.cg_2.messages.responses.actions.*;
import it.polimi.ingsw.cg_2.view.gameplayer.MessageVisitor;

import java.util.List;
import java.util.Map;

/**
 * A visitor class for the Visitor Pattern to visit incoming messages and display them on
 * a Command Line Interface.
 */
public class CliMessageVisitor implements MessageVisitor {

    private int playerNum;

    public CliMessageVisitor() {

        playerNum = -1;

    }

    public int getPlayerNum() {

        return playerNum;

    }

    public void setPlayerNum(int playerNum) {

        this.playerNum = playerNum;

    }

    private void cli(String string) {

        System.out.println(string);

    }

    /**
     * Takes a coordinate in the format 0:11 and converts it to the format A:12.
     *
     * @param string the coordinate in the XX:YY format (stating from 0:0)
     * @return the coordinate in the X:00 format (starting from A:1)
     */
    private static String coordToHuman(String string) {

        String[] split = string.split(":");

        int col = 'A' + Integer.parseInt(split[0]);
        int row = Integer.parseInt(split[1]) + 1;

        return (char) col + ":" + Integer.toString(row);

    }

    ////////////////////////////////////// BROADCAST \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    @Override
    public void display(AttackBroadcastMsg msg) {

        if (playerNum == msg.getPlayer()) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        sb.append("Player");
        sb.append(msg.getPlayer());
        sb.append(" attacked in sector ");
        sb.append(coordToHuman(msg.getCoordinate()));
        sb.append(". ");

        sb.append(attackToString(msg.getKills(), msg.getSurvivors()));

        cli(sb.toString());

    }

    @Override
    public void display(ChatBroadcastMsg msg) {

        StringBuilder sb = new StringBuilder();

        sb.append("Player");
        sb.append(msg.getPlayer());
        sb.append(": ");
        sb.append(msg.getMessage());

        cli(sb.toString());

    }

    @Override
    public void display(EscapeBroadcastMsg msg) {

        if (playerNum == msg.getPlayer()) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        sb.append("Player");
        sb.append(msg.getPlayer());

        if (msg.getCardType().equals("RED")) {

            sb.append(" tried to escape from hatch ");
            sb.append(coordToHuman(msg.getCoordinate()));
            sb.append(" but it broke.");
            cli(sb.toString());
            return;

        }

        sb.append(" escaped from hatch ");
        sb.append(coordToHuman(msg.getCoordinate()));
        sb.append(".");

        cli(sb.toString());

        sb = new StringBuilder();
        sb.append("Turn passed to Player");
        sb.append(msg.getNextPlayer());
        sb.append(", new turn number is ");
        sb.append(msg.getNewTurn());
        sb.append(".");

        cli(sb.toString());

    }

    @Override
    public void display(NoiseBroadcastMsg msg) {

        if (playerNum == msg.getPlayer()) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        sb.append("Player");
        sb.append(msg.getPlayer());
        sb.append(" made ");
        sb.append(msg.getCardType());

        if (msg.getCardType().equals("NOISE")) {
            sb.append(" in sector ");
            sb.append(coordToHuman(msg.getCoordinate()));
        }

        if (msg.isItem()) {
            sb.append(" and found an item");
        }

        sb.append(".");

        cli(sb.toString());

    }

    @Override
    public void display(PassBroadcastMsg msg) {

        StringBuilder sb = new StringBuilder();

        sb.append("Turn passed to Player");
        sb.append(msg.getNewPlayer());
        sb.append(", new turn number is ");
        sb.append(msg.getNewTurn());
        sb.append(".");

        cli(sb.toString());

    }

    @Override
    public void display(UseAdrItemBroadcastMsg msg) {

        if (playerNum == msg.getPlayer()) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        sb.append("Player");
        sb.append(msg.getPlayer());
        sb.append(" used ADRENALINE.");

        cli(sb.toString());

    }

    @Override
    public void display(UseAtkItemBroadcastMsg msg) {

        if (playerNum == msg.getPlayer()) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        sb.append("Player");
        sb.append(msg.getPlayer());
        sb.append(" used ATTACK item in sector ");
        sb.append(" attacked in sector ");
        sb.append(coordToHuman(msg.getCoordinate()));
        sb.append(". ");

        sb.append(attackToString(msg.getKills(), msg.getSurvivors()));

        cli(sb.toString());

    }

    @Override
    public void display(UseSdtItemBroadcastMsg msg) {

        if (playerNum == msg.getPlayer()) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        sb.append("Player");
        sb.append(msg.getPlayer());
        sb.append(" used SEDATIVES.");

        cli(sb.toString());

    }

    @Override
    public void display(UseSptItemBroadcastMsg msg) {

        if (playerNum == msg.getPlayer()) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        sb.append("Player");
        sb.append(msg.getPlayer());
        sb.append(" used SPOTLIGHT in sector ");
        sb.append(coordToHuman(msg.getCoordinate()));
        sb.append(". ");

        if (!msg.getSpottedPlayers().isEmpty()) {
            sb.append("Spotlight revealed ");
            for (Map.Entry<Integer, String> p : msg.getSpottedPlayers().entrySet()) {

                sb.append("Player");
                sb.append(p.getKey());
                sb.append("[" + coordToHuman(p.getValue()) + "] ");

            }
            sb.append(". ");
        } else {
            sb.append("No one has been revealed.");
        }

        cli(sb.toString());

    }

    @Override
    public void display(UseTlpItemBroadcastMsg msg) {

        if (playerNum == msg.getPlayer()) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        sb.append("Player");
        sb.append(msg.getPlayer());
        sb.append(" used TELEPORT.");

        cli(sb.toString());

    }

    @Override
    public void display(GameStartedBroadcastMsg msg) {

        StringBuilder sb = new StringBuilder();

        sb.append("Game");
        sb.append(msg.getGameNumber());
        sb.append(" started in zone ");
        sb.append(msg.getZoneName());
        sb.append(", game mode is ");
        sb.append(msg.getGameMode());
        sb.append(" and there are ");
        sb.append(msg.getNumberOfPlayers());
        sb.append(" players.");

        sb.append("\nThe turn starts from Player");
        sb.append(msg.getPlayerTurn());
        sb.append(".");

        cli(sb.toString());

    }

    @Override
    public void display(GameFinishedBroadcastMsg msg) {

        StringBuilder sb = new StringBuilder();

        sb.append("Game finished!");

        if (!msg.getWinners().isEmpty()) {
            sb.append("\nWinners are");
            for (Map.Entry<Integer, String> p : msg.getWinners().entrySet()) {

                sb.append(" Player");
                sb.append(p.getKey());
                sb.append("(" + p.getValue() + ")");

            }
            sb.append(".");
        }

        if (!msg.getLosers().isEmpty()) {
            sb.append("\nLosers are ");
            for (Map.Entry<Integer, String> p : msg.getLosers().entrySet()) {

                sb.append(" Player");
                sb.append(p.getKey());
                sb.append("(" + p.getValue() + ")");

            }
            sb.append(".");
        }

        cli(sb.toString());


    }

    ////////////////////////////////////// RESPONSES \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    @Override
    public void display(AttackResponseMsg msg) {

        StringBuilder sb = new StringBuilder();

        sb.append("You attacked in sector ");
        sb.append(coordToHuman(msg.getCoordinate()));
        sb.append(". ");

        sb.append(attackToString(msg.getKills(), msg.getSurvivors()));

        cli(sb.toString());

    }

    @Override
    public void display(DrawResponseMsg msg) {

        StringBuilder sb = new StringBuilder();

        sb.append("You found a ");
        sb.append(msg.getCardType());
        sb.append(" sector card");

        if (!msg.getItemType().equals("")) {

            sb.append(" and a ").append(msg.getItemType()).append(" item");

        }

        sb.append(".");

        cli(sb.toString());

    }

    @Override
    public void display(EscapeResponseMsg msg) {

        StringBuilder sb = new StringBuilder();

        sb.append("You");

        if (msg.getCardType().equals("RED")) {

            sb.append(" tried to escape from hatch ");
            sb.append(coordToHuman(msg.getPosition()));
            sb.append(" but it broke.");
            cli(sb.toString());
            return;

        }

        sb.append(" escaped from hatch ");
        sb.append(coordToHuman(msg.getPosition()));
        sb.append(".");

        cli(sb.toString());

        sb = new StringBuilder();
        sb.append("Turn passed to Player").append(msg.getNextPlayer());
        sb.append(", new turn number is ");
        sb.append(msg.getNewTurn());
        sb.append(".");

        cli(sb.toString());


    }

    @Override
    public void display(MoveResponseMsg msg) {

        StringBuilder sb = new StringBuilder();

        sb.append("You moved to ");
        sb.append(coordToHuman(msg.getCoordinate()));
        sb.append(" in a ");
        sb.append(msg.getSectorType());
        sb.append(" sector.");

        cli(sb.toString());

    }

    @Override
    public void display(NoiseResponseMsg msg) {

        StringBuilder sb = new StringBuilder();

        sb.append("You made noise in sector ");
        sb.append(coordToHuman(msg.getCoordinate()));
        sb.append(".");

        cli(sb.toString());

    }

    @Override
    public void display(PassResponseMsg msg) {

        // Do nothing, use the broadcast message to display the same info

    }

    @Override
    public void display(UseAdrItemResponseMsg msg) {

        cli("You used ADRENALINE.");

    }

    @Override
    public void display(UseAtkItemResponseMsg msg) {

        StringBuilder sb = new StringBuilder();

        sb.append("You used ATTACK in sector ");
        sb.append(coordToHuman(msg.getCoordinate()));
        sb.append(". ");

        sb.append(attackToString(msg.getKills(), msg.getSurvivors()));

        cli(sb.toString());

    }

    @Override
    public void display(UseSdtItemResponseMsg msg) {

        cli("You used SEDATIVES.");

    }

    @Override
    public void display(UseSptItemResponseMsg msg) {

        StringBuilder sb = new StringBuilder();

        sb.append("You used SPOTLIGHT in sector ");
        sb.append(coordToHuman(msg.getCoordinate())).append(". ");

        if (!msg.getSpottedPlayers().isEmpty()) {
            sb.append("Spotlight revealed ");
            for (Map.Entry<Integer, String> p : msg.getSpottedPlayers().entrySet()) {

                sb.append("Player").append(p.getKey());
                sb.append("[" + coordToHuman(p.getValue()) + "] ");

            }
            sb.append(". ");
        } else {
            sb.append("No one has been revealed.");
        }

        cli(sb.toString());

    }

    @Override
    public void display(UseTlpItemResponseMsg msg) {

        cli("You used TELEPORT.");

    }

    @Override
    public void display(ConnectionResponseMsg msg) {

        // Do nothing

    }

    @Override
    public void display(SubscribeResponseMsg msg) {

        // Do nothing

    }

    @Override
    public void display(AckResponseMsg msg) {

        // Do nothing

    }

    @Override
    public void display(PrivateDataResponseMsg msg) {

        // Player number, when set, will be used to filter out some messages
        // (If it was a problem getting it from the token)
        if (playerNum == -1) {
            playerNum = msg.getPlayerNumber();
        }

        StringBuilder sb = new StringBuilder();

        sb.append("You are the Player");
        sb.append(msg.getPlayerNumber());
        sb.append(" and you rank is ");
        sb.append(msg.getRank());
        sb.append(" of ");
        sb.append(msg.getRace());
        sb.append(" race. ");

        sb.append("You are in position ");
        sb.append(coordToHuman(msg.getPosition()));
        sb.append(".");

        if (!msg.getHeldItems().isEmpty()) {
            sb.append("\nYou have the following items:");
            for (String item : msg.getHeldItems()) {
                sb.append(" ");
                sb.append(item);
            }
            sb.append(".");
        }

        if (!msg.getActivatedItems().isEmpty()) {
            sb.append("\nYou activated the following items:");
            for (String item : msg.getActivatedItems()) {
                sb.append(" ");
                sb.append(item);
            }
            sb.append(".");
        }

        cli(sb.toString());

    }

    @Override
    public void display(PublicLogResponseMsg msg) {

        if (msg.getLog().isEmpty()) {
            cli("Public log is currently empty.");
            return;
        }

        cli("######################### PRINTING LOG START #########################");
        cli("########################## PRINTING LOG END ##########################");

        for (BroadcastMsg message : msg.getLog()) {
            message.display(this);
        }

    }

    @Override
    public void display(InvalidRequestMsg msg) {

        StringBuilder sb = new StringBuilder();

        sb.append("ERROR: ");
        sb.append(msg.getReason());

        cli(sb.toString());

    }

    private String attackToString(Map<Integer, String> kills, List<Integer> survivors) {

        StringBuilder sb = new StringBuilder();

        if (!kills.isEmpty()) {
            for (Map.Entry<Integer, String> p : kills.entrySet()) {

                sb.append("Player");
                sb.append(p.getKey());
                sb.append("[" + p.getValue() + "] ");

            }
            sb.append("died. ");
        } else {
            sb.append("No one died.");
        }

        if (!survivors.isEmpty()) {
            for (int p : survivors) {

                sb.append("Player");
                sb.append(p);
                sb.append("[" + p + "] ");

            }
            sb.append("defended. ");
        }

        return sb.toString();

    }


}
