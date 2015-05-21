package it.polimi.ingsw.cg_2.model.player;

import static it.polimi.ingsw.cg_2.model.player.CharacterRace.*;

/**
 * The possible ranks of a character, for every rank it is possible to get the
 * relative default {@link CharacterRace}. It is the "default" one because an
 * human character, for example, could become an alien during a game.
 */
public enum CharacterRank {

    CAPTAIN(HUMAN),
    PILOT(HUMAN),
    PSYCHOLOGIST(HUMAN),
    SOLDIER(HUMAN),
    FIRST(ALIEN),
    SECOND(ALIEN),
    THIRD(ALIEN),
    FOURTH(ALIEN);

    private CharacterRace defaultType;

    CharacterRank(CharacterRace defaultType) {

        this.defaultType = defaultType;

    }

    /**
     * Gets the default {@link CharacterRace} for this rank.
     *
     * @return the default type relative to this rank
     */
    public CharacterRace getDefaultType() {

        return defaultType;

    }

}
