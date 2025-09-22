package net.minestom.testing;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.Instance;
import net.minestom.server.network.packet.server.ServerPacket;

/**
 * The {@link TestConnection} represents a connection from a player to a test server instance.
 * It provides methods to connect a player to an instance and track incoming packets.
 *
 * @version 1.0.0
 * @since 0.1.0
 */
public interface TestConnection {

    /**
     * Connects a player to the given instance at the specified position.
     *
     * @param instance the instance to connect to
     * @param pos      the position to connect at
     * @return the connected player
     */
    Player connect(Instance instance, Pos pos);

    /**
     * Connects a player to the given instance at the default position (0, 0, 0).
     *
     * @param instance the instance to connect to
     * @return the connected player
     */
    default Player connect(Instance instance) {
        return connect(instance, Pos.ZERO);
    }

    /**
     * Tracks incoming packets of the specified type.
     *
     * @param type the class of the packet type to track
     * @param <T>  the type of the packet
     * @return a collector for the specified packet type
     */
    <T extends ServerPacket> Collector<T> trackIncoming(Class<T> type);

    /**
     * Tracks incoming packets of the default type {@link ServerPacket}.
     *
     * @return a collector for the default packet type
     */
    default Collector<ServerPacket> trackIncoming() {
        return trackIncoming(ServerPacket.class);
    }
}
