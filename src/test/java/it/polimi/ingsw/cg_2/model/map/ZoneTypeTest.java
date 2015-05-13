package it.polimi.ingsw.cg_2.model.map;

import static org.junit.Assert.*;

import org.junit.Test;

public class ZoneTypeTest {

    @Test
    public void shouldContainLiterals() {

        assertNotNull(ZoneType.values().length > 0);

    }

    @Test
    public void shouldContainPNGFileNames() {

        for (ZoneType type : ZoneType.values()) {
            assertTrue(type.getFileName().contains(".png")
                    || type.getFileName().contains(".PNG"));
        }

    }

}
