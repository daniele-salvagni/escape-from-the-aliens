package it.polimi.ingsw.cg_2.model.player;

import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterRaceTest {

    @Test
    public void alienLiteralShouldExist() {

        assertNotNull(CharacterRace.valueOf("HUMAN"));

    }

    @Test
    public void humanLiteralsShouldExist() {

        assertNotNull(CharacterRace.valueOf("HUMAN"));

    }

}
