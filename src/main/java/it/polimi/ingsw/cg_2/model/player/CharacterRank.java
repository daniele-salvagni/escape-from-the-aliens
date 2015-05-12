package it.polimi.ingsw.cg_2.model.player;

import static it.polimi.ingsw.cg_2.model.player.CharacterType.*;

/**
 * The possible ranks of a character, for every rank it is possible to get the
 * relative default {@link CharacterType}. It is the "default" one because an
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

    private CharacterType defaultType;

    CharacterRank(CharacterType defaultType) {

        this.defaultType = defaultType;

    }

    /**
     * Gets the default {@link CharacterType} for this rank.
     *
     * @return the default type relative to this rank
     */
    public CharacterType getDefaultType() {

        return defaultType;

    }

}
