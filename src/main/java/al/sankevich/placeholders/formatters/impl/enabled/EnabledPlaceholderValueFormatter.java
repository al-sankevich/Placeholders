package al.sankevich.placeholders.formatters.impl.enabled;

import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.formatters.PlaceholderValueFormatter;
import al.sankevich.placeholders.formatters.impl.AbstractPlaceholderValueFormatter;

/**
 * General implementation of enabled by default {@link PlaceholderValueFormatter}.
 *
 * @param <T> the expected placeholder type.
 */
public abstract class EnabledPlaceholderValueFormatter<T extends Placeholder> extends AbstractPlaceholderValueFormatter<T> {

    /**
     * Builds {@code EnabledPlaceholderValueFormatter} with provided
     * disabling {@link PlaceholderValueFormatter#getFormatName format name}.
     *
     * @param formatName the disabling {@link PlaceholderValueFormatter#getFormatName format name}.
     */
    public EnabledPlaceholderValueFormatter(final String formatName) {
        super(true, formatName);
    }

}
