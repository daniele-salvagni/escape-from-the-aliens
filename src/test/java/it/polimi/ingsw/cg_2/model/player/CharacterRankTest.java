package it.polimi.ingsw.cg_2.model.player;

import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterRankTest {

    @Test
    public void literalsShouldExist() {

        assertNotNull(CharacterRank.valueOf("CAPTAIN"));
        assertNotNull(CharacterRank.valueOf("PILOT"));
        assertNotNull(CharacterRank.valueOf("PSYCHOLOGIST"));
        assertNotNull(CharacterRank.valueOf("SOLDIER"));
        assertNotNull(CharacterRank.valueOf("FIRST"));
        assertNotNull(CharacterRank.valueOf("SECOND"));
        assertNotNull(CharacterRank.valueOf("THIRD"));
        assertNotNull(CharacterRank.valueOf("FOURTH"));

    }

    @Test
    public void shouldBeHuman() {

        assertEquals(CharacterRank.CAPTAIN.getDefaultType(),
                CharacterRace.HUMAN);
        assertEquals(CharacterRank.PILOT.getDefaultType(),
                CharacterRace.HUMAN);
        assertEquals(CharacterRank.PSYCHOLOGIST.getDefaultType(),
                CharacterRace.HUMAN);
        assertEquals(CharacterRank.SOLDIER.getDefaultType(),
                CharacterRace.HUMAN);

    }

    @Test
    public void shouldBeAlien() {

        assertEquals(CharacterRank.FIRST.getDefaultType(),
                CharacterRace.ALIEN);
        assertEquals(CharacterRank.SECOND.getDefaultType(),
                CharacterRace.ALIEN);
        assertEquals(CharacterRank.THIRD.getDefaultType(),
                CharacterRace.ALIEN);
        assertEquals(CharacterRank.FOURTH.getDefaultType(),
                CharacterRace.ALIEN);
        
    }

}
