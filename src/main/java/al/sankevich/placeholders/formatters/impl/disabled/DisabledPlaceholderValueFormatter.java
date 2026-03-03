package al.sankevich.placeholders.formatters.impl.disabled;

import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.formatters.PlaceholderValueFormatter;
import al.sankevich.placeholders.formatters.impl.AbstractPlaceholderValueFormatter;

/**
 * General implementation of disabled by default {@link PlaceholderValueFormatter}.
 *
 * @param <T> the expected placeholder type.
 */
public abstract class DisabledPlaceholderValueFormatter<T extends Placeholder> extends AbstractPlaceholderValueFormatter<T> {

    /**
     * Builds {@code DisabledPlaceholderValueFormatter} with provided enabling {@link PlaceholderValueFormatter#getFormatName format name}.
     *
     * @param formatName the enabling {@link PlaceholderValueFormatter#getFormatName format name}.
     */
    public DisabledPlaceholderValueFormatter(final String formatName) {
        super(false, formatName);
    }

}
