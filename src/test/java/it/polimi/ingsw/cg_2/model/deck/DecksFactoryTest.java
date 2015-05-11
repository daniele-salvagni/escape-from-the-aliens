package it.polimi.ingsw.cg_2.model.deck;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_2.model.GameMode;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DecksFactoryTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldCreateAStandardDecksFactory() throws Exception {

        DecksFactory decksFactory = DecksFactory.create(GameMode.ADVANCED);
        assertTrue(decksFactory instanceof StandardDecksFactory);

    }

    @Test
    public void shouldThrowException() throws Exception {

        thrown.expect(IllegalArgumentException.class);
        DecksFactory.create(null);

    }

}
