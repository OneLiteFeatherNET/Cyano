package net.minestom.testing;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * A utility interface for collecting and asserting on collections of elements in tests.
 * <p>
 * This interface provides convenient methods for collecting elements and performing
 * common test assertions, such as verifying collection size, checking single elements,
 * and validating element types. It's particularly useful in testing scenarios where
 * you need to validate the contents of collections with fluent assertion methods.
 *
 * @param <T> the type of elements this collector can collect
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Collector<T> {

    /**
     * Collects the elements of this collector into a list.
     *
     * @return a list of elements collected by this collector
     */
    @NotNull List<@NotNull T> collect();

    /**
     * Assert a single element from the collection, check its type, and apply a consumer to it.
     *
     * @param type     the expected type of the single element
     * @param consumer the consumer to apply to the single element
     * @param <P>      the type of the single element
     */
    default <P extends T> void assertSingle(@NotNull Class<P> type, @NotNull Consumer<P> consumer) {
        List<T> elements = collect();
        assertEquals(1, elements.size(), "Expected 1 element, got " + elements);
        T element = elements.getFirst();
        assertInstanceOf(type, element, "Expected type " + type.getSimpleName() + ", got " + element.getClass().getSimpleName());
        consumer.accept((P) element);
    }

    /**
     * Assert a single element from the collection and apply a consumer to it.
     *
     * @param consumer the consumer to apply to the single element
     */
    default void assertSingle(@NotNull Consumer<T> consumer) {
        List<T> elements = collect();
        assertEquals(1, elements.size(), "Expected 1 element, got " + elements);
        consumer.accept(elements.getFirst());
    }

    /**
     * Assert the count of elements in the collector.
     *
     * @param count the expected number of elements
     */
    default void assertCount(int count) {
        List<T> elements = collect();
        assertEquals(count, elements.size(), "Expected " + count + " element(s), got " + elements.size() + ": " + elements);
    }

    /**
     * Assert that the collector only contains a single element.
     */
    default void assertSingle() {
        assertCount(1);
    }

    /**
     * Assert that the collector is empty.
     */
    default void assertEmpty() {
        assertCount(0);
    }
}
