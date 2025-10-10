package net.minestom.testing;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.Instance;
import net.minestom.testing.extension.MicrotusExtension;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MicrotusExtension.class)
class TestPlayerIntegrationTest {

    @Test
    void testCustomPlayerCreation(@NotNull Env env) {
        Instance instance = env.createFlatInstance();
        assertNotNull(instance);
        Player player = env.createPlayer(instance);
        assertNotNull(player, "Player should not be null after creation");
        assertInstanceOf(Player.class, player, "Player should be an instance of Player class");
        assertInstanceOf(TestPlayerImpl.class, player, "Player should be an instance of TestPlayerImpl class");
        assertEquals(instance, player.getInstance(), "Player should be in the created instance");
        assertEquals(Pos.ZERO, player.getPosition(), "Player should start at position (0, 0, 0)");
        assertEquals("RandName", player.getUsername(), "Player should have a random name");

        env.destroyInstance(instance, true);
    }
}
