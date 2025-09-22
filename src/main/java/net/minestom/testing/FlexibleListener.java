package net.minestom.testing;

import net.minestom.server.event.Event;

import java.util.function.Consumer;

/**
 * A flexible listener interface for handling events in a test environment.
 *
 * @param <E> the type of event this listener handles
 * @version 1.0.0
 * @since 1.0.0
 */
public interface FlexibleListener<E extends Event> {

    /**
     * Sets a followup handler to process the next received event.
     * This handler will be called when the next event of type {@code E} is received.
     * Calling this method replaces any previously set followup handler.
     *
     * @param handler the consumer to handle the next event
     */
    void followup( Consumer<E> handler);

    /**
     * Empty followup handler.
     */
    default void followup() {
        followup(event -> {
            // Empty
        });
    }

    /**
     * Fails if an event is received. Valid until the next followup call.
     */
    void failFollowup();
}
