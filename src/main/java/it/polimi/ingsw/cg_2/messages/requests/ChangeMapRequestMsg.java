package it.polimi.ingsw.cg_2.messages.requests;

import it.polimi.ingsw.cg_2.messages.Token;

/**
 *
 */
public class ChangeMapRequestMsg extends RequestMsg {

    private final String map;

    public ChangeMapRequestMsg(Token token, String map) {

        super(token);

        if (!map.matches("^(ASCESA|BALENA|CAVOUR|DILEMMA|EN_GARDE|FERMI|FRENZY|GALILEI" +
                "|GARIBALDI|GALVANI|MACHIAVELLI|MORGENLAND|SINISTRA|SOCRATES" +
                "|SOUND_OF_SILENCE)$")) {
            throw new IllegalArgumentException("Invalid MAP.");
        }

        this.map = map;

    }

    public String getMap() {

        return map;

    }

}
