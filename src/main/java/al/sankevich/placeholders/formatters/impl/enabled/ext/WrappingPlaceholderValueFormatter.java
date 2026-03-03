package al.sankevich.placeholders.formatters.impl.enabled.ext;

import al.sankevich.placeholders.constants.FormatingConstants;
import al.sankevich.placeholders.dtos.WrappablePlaceholder;
import al.sankevich.placeholders.engines.string.StringEngine;
import al.sankevich.placeholders.formatters.PlaceholderValueFormatter;
import al.sankevich.placeholders.formatters.impl.enabled.EnabledPlaceholderValueFormatter;

import static al.sankevich.placeholders.constants.FormatingConstants.NO_WRAP;

/**
 * This implementation of {@link PlaceholderValueFormatter} is responsible
 * for wrapping {@link WrappablePlaceholder placeholder}'s replacement value
 * with {@link WrappablePlaceholder#getWrapper() delimiter}.
 */
public class WrappingPlaceholderValueFormatter extends EnabledPlaceholderValueFormatter<WrappablePlaceholder> {

    /**
     * Builds {@code WrappingPlaceholderValueFormatter}
     * with custom disabling {@link PlaceholderValueFormatter#getFormatName format name}.
     *
     * @param formatName the custom disabling {@link PlaceholderValueFormatter#getFormatName format name}.
     */
    public WrappingPlaceholderValueFormatter(final String formatName) {
        super(formatName);
    }

    /**
     * Builds {@code WrappingPlaceholderValueFormatter}
     * with {@link FormatingConstants#NO_WRAP default} disabling {@link PlaceholderValueFormatter#getFormatName format name}.
     */
    public WrappingPlaceholderValueFormatter() {
        super(NO_WRAP);
    }

    @Override
    public void format(
            final WrappablePlaceholder placeholder,
            final String formatValue,
            final StringEngine buffer
    ) {
        char delimiter = placeholder.getWrapper();
        buffer.addToStart(delimiter).addToEnd(delimiter);
    }

}
