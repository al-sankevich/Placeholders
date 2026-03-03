package al.sankevich.placeholders.engines.string;

import al.sankevich.placeholders.dtos.Assignable;
import al.sankevich.placeholders.dtos.Position;

/**
 * Implementations of this interface are responsible for providing API for manipulating
 * with {@link String strings} without generating intermediate objects.
 */
public interface StringEngine extends Appendable, CharSequence, Assignable {

    /**
     * Replaces the substring in provided {@link Position position} with provided {@code replacement}.
     *
     * @param position    the substring's {@link Position position}, which required to be replaced.
     * @param replacement the substring's replacement {@link String string}.
     * @return the {@link StringEngine}.
     */
    StringEngine replace(Position position, String replacement);

    /**
     * Replaces all occurrences of provided {@code target} with {@code replacement}.
     *
     * @param target      the substring to be replaced.
     * @param replacement the substring's replacement {@link String string}.
     * @return the {@link StringEngine}.
     */
    StringEngine replaceAll(String target, String replacement);

    /**
     * Adds the provided {@code character} to the end.
     *
     * @param character the {@link Character character} to be added.
     * @return the {@link StringEngine}.
     */
    StringEngine addToEnd(char character);

    /**
     * Adds the provided {@code character} to the beginning.
     *
     * @param character the {@link Character character} to be added.
     * @return the {@link StringEngine}.
     */
    StringEngine addToStart(char character);

    /**
     * Builds result {@link String string} and clears building buffer.
     *
     * @return the result {@link String string}.
     */
    String toStringAndClear();
}
