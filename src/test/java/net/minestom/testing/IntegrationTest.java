package net.minestom.testing;

import net.minestom.testing.extension.MicrotusExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MicrotusExtension.class)
class IntegrationTest {

    @Test
    void testEnv(Env env) {
        Assertions.assertNotNull(env);
        Assertions.assertNotNull(env.process());
    }
}
