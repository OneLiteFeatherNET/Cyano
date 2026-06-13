package net.minestom.testing;

import net.minestom.server.ServerFlag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EnvironmentTest {

    @Test
    void insideTest() {
        Assertions.assertTrue(ServerFlag.INSIDE_TEST);
    }
}
