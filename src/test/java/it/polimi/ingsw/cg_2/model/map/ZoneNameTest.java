package it.polimi.ingsw.cg_2.model.map;

import static org.junit.Assert.*;

import org.junit.Test;

public class ZoneNameTest {

    @Test
    public void shouldContainLiterals() {

        assertNotNull(ZoneName.values().length > 0);

    }

    @Test
    public void shouldContainPNGFileNames() {

        for (ZoneName type : ZoneName.values()) {
            assertTrue(type.getFileName().contains(".png")
                    || type.getFileName().contains(".PNG"));
        }

    }

}
