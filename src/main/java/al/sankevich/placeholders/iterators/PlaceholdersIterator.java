package al.sankevich.placeholders.iterators;

import al.sankevich.placeholders.dtos.Placeholder;

/**
 * Implementations of this interface are responsible for providing API
 * for iterating through {@link T placeholders} of {@code source}.
 *
 * @param <T> the expected placeholder type.
 */
public interface PlaceholdersIterator<T extends Placeholder> {

    /**
     * Provides next {@link T placeholder} from {@code source}.
     *
     * @return next {@link T placeholder} if exists, otherwise {@code null}.
     */
    T get();

    /**
     * Checks if next {@link T placeholder} exists.
     *
     * @return {@code true} if next {@link T placeholder} exists, otherwise {@code false}.
     */
    boolean hasNext();
}
