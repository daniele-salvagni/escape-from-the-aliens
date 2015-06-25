package it.polimi.ingsw.cg_2.view.gameplayer.cli;

import it.polimi.ingsw.cg_2.messages.Token;
import it.polimi.ingsw.cg_2.messages.requests.ChangeMapRequestMsg;
import it.polimi.ingsw.cg_2.messages.requests.RequestMsg;
import it.polimi.ingsw.cg_2.messages.requests.actions.*;

/**
 * This class interpret command line commands and converts them into request messages.
 */
public class CliInterpteter {

    /**
     * Interpret command line commands and converts them into request messages.
     *
     * @param token the client token to generate the request
     * @param cmd the command string
     * @return the corresponding action, null if the command was not well formed
     */
    public RequestMsg parseString(Token token, String cmd) {

        if (cmd.matches("^(map\\s(ASCESA|BALENA|CAVOUR|DILEMMA|EN_GARDE|FERMI" +
                "|FRENZY|GALILEI|GARIBALDI|GALVANI|MACHIAVELLI|MORGENLAND|SINISTRA" +
                "|SOCRATES|SOUND_OF_SILENCE))$")) {

            String param = cmd.replaceFirst("map ", "");
            return new ChangeMapRequestMsg(token, param);

        } else if (cmd.matches("^move\\s(([0-9]|[1-9][0-9]):([0-9]|[1-9][0-9]))$")) {

            String param = cmd.replaceFirst("move ", "");
            return new MoveRequestMsg(token, param);

        } else if (cmd.matches("^attack$")) {

            return new AttackRequestMsg(token);

        } else if (cmd.matches("^draw$")) {

            return new DrawRequestMsg(token);

        } else if (cmd.matches("^escape$")) {

            return new EscapeRequestMsg(token);

        } else if (cmd.matches("^noise\\s(([0-9]|[1-9][0-9]):([0-9]|[1-9][0-9]))$")) {

            String param = cmd.replaceFirst("noise ", "");
            return new NoiseRequestMsg(token, param);

        } else if (cmd.matches("^pass$")) {

            return new PassRequestMsg(token);

        } else if (cmd.matches("^chat\\s(.+)$")) {

            String param = cmd.replaceFirst("chat ", "");
            return new SendChatMsg(token, param);

        } else if (cmd.matches("^use\\s(adrenaline|attack|sedatives|teleport)$")) {

            String param = cmd.replaceFirst("use ", "");

            if (param.matches("adrenaline")) {

                return new UseAdrRequestMsg(token);

            } else if (param.matches("attack")) {

                return new UseAtkRequestMsg(token);

            } else if (param.matches("sedatives")) {

                return new UseSdtRequestMsg(token);

            } else { // teleport

                return new UseTlpRequestMsg(token);

            }

        } else if (cmd.matches("^use\\sspotlight\\s(([0-9]|[1-9][0-9]):" +
                "([0-9]|[1-9][0-9]))$")) {

            String param = cmd.replaceFirst("use spotlight ", "");
            return new UseSptRequestMsg(token, param);

        } else {

            return null;

        }


    }


}
