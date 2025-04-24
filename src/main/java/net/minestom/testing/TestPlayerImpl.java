package net.minestom.testing;

import net.minestom.server.entity.Player;
import net.minestom.server.instance.Chunk;
import net.minestom.server.network.player.GameProfile;
import net.minestom.server.network.player.PlayerConnection;
import org.jetbrains.annotations.NotNull;

/**
 * The test environment can't really use a real player for the test.
 * The {@link TestPlayerImpl} overrides some methods to send data immediately, otherwise the test framework runs into issues.
 *
 * @version 1.0.0
 * @since 1.0.0
 */
public class TestPlayerImpl extends Player {

    /**
     * Creates a new test player.
     *
     * @param playerConnection the player's connection
     * @param gameProfile      the player's game profile
     */
    public TestPlayerImpl(@NotNull PlayerConnection playerConnection, @NotNull GameProfile gameProfile) {
        super(playerConnection, gameProfile);
    }

    /**
     * Send data from a chunk immediately.
     *
     * @param chunk the chunk to send
     */
    @Override
    public void sendChunk(@NotNull Chunk chunk) {
        // Send immediately
        sendPacket(chunk.getFullDataPacket());
    }
}
