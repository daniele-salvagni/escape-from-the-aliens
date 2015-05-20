package it.polimi.ingsw.cg_2.model.map;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ZoneTypeTest {

    @Test
    public void shouldContainLiterals() {

        assertNotNull(ZoneType.values().length > 0);

    }

}
