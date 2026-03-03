package al.sankevich.placeholders.formatters;

import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.engines.string.StringEngine;

/**
 * Implementations of this interface are responsible for formatting {@link T placeholder}'s replacement value.
 *
 * @param <T> the expected placeholder type.
 */
public interface PlaceholderValueFormatter<T extends Placeholder> {

    /**
     * Formats {@code buffer}.
     *
     * @param placeholder the parsed {@link T placeholder}.
     * @param formatValue the enabling / disabling format value.
     * @param buffer      the {@link T placeholder}'s replacement value.
     */
    void format(T placeholder, String formatValue, StringEngine buffer);

    /**
     * @return {@code true}, if formatter enabled by default, otherwise {@code false}.
     */
    boolean isEnabledByDefault();

    /**
     * @return the format's name, which enables / disables this formatter.
     */
    String getFormatName();

}
