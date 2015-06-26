package it.polimi.ingsw.cg_2.messages.requests;

import it.polimi.ingsw.cg_2.messages.Token;

/**
 * A message to request to change the map (Zone) of the game (while in lobby).
 */
public class ChangeMapRequestMsg extends RequestMsg {

    private final String map;

    /**
     * Create a new ChangeMapRequestMsg reqeust message.
     *
     * @param token the token that identifies the client
     * @param map   the name of the map to change to, available maps are ASCESA, BALENA,
     *              CAVOUR, DILEMMA, EN_GARDE, FERMI, FRENZY, GALILEI, GARIBALDI, GALVANI,
     *              MACHIAVELLI, MORGENLAND, SINISTRA, SOCRATES, SOUND_OF_SILENCE
     */
    public ChangeMapRequestMsg(Token token, String map) {

        super(token);

        if (!map.matches("^(ASCESA|BALENA|CAVOUR|DILEMMA|EN_GARDE|FERMI|FRENZY|GALILEI" +
                "|GARIBALDI|GALVANI|MACHIAVELLI|MORGENLAND|SINISTRA|SOCRATES" +
                "|SOUND_OF_SILENCE)$")) {
            throw new IllegalArgumentException("Invalid MAP.");
        }

        this.map = map;

    }

    /**
     * Get the name of the map to change to, available maps are: ASCESA, BALENA, CAVOUR,
     * DILEMMA, EN_GARDE, FERMI, FRENZY, GALILEI, GARIBALDI, GALVANI, MACHIAVELLI,
     * MORGENLAND, SINISTRA, SOCRATES, SOUND_OF_SILENCE
     *
     * @return the name of the map to change to
     */
    public String getMap() {

        return map;

    }

}
