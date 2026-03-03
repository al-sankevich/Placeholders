package al.sankevich.placeholders.iterators.editable;

import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.iterators.PlaceholdersIterator;

/**
 * Implementations of this interface are responsible
 * for providing extended {@link PlaceholdersIterator} API with editing ability.
 *
 * @param <T> the expected placeholder type.
 */
public interface EditablePlaceholdersIterator<T extends Placeholder> extends PlaceholdersIterator<T> {

    /**
     * Replaces the last checked {@link T placeholder}
     * in {@code source} with the value from provided parameter {@code value}.
     *
     * @param value the placeholder's replacement value
     */
    void replace(String value);

    /**
     * Provides the copy of {@code source} with replaced {@link T placeholders},
     * for which was invoked {@link #replace} method.
     *
     * @return the copy of {@code source} with replaced {@link T placeholders}.
     */
    String result();
}
