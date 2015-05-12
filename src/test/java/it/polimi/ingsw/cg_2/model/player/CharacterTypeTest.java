package it.polimi.ingsw.cg_2.model.player;

import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterTypeTest {

    @Test
    public void alienLiteralShouldExist() {

        assertNotNull(CharacterType.valueOf("HUMAN"));

    }

    @Test
    public void humanLiteralsShouldExist() {

        assertNotNull(CharacterType.valueOf("HUMAN"));

    }

}
