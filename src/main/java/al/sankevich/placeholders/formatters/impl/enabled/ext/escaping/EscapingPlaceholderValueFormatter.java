package al.sankevich.placeholders.formatters.impl.enabled.ext.escaping;

import al.sankevich.placeholders.constants.FormatingConstants;
import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.formatters.PlaceholderValueFormatter;
import al.sankevich.placeholders.formatters.impl.enabled.EnabledPlaceholderValueFormatter;

import static al.sankevich.placeholders.constants.FormatingConstants.NO_ESCAPE;

/**
 * General implementation of {@link EnabledPlaceholderValueFormatter}.
 */
public abstract class EscapingPlaceholderValueFormatter extends EnabledPlaceholderValueFormatter<Placeholder> {

    /**
     * Builds {@code EscapingPlaceholderValueFormatter} with {@link FormatingConstants#NO_ESCAPE default}
     * disabling {@link PlaceholderValueFormatter#getFormatName format name}.
     */
    public EscapingPlaceholderValueFormatter() {
        super(NO_ESCAPE);
    }

    /**
     * Builds {@code EscapingPlaceholderValueFormatter} with custom
     * disabling {@link PlaceholderValueFormatter#getFormatName format name}.
     *
     * @param formatName the custom disabling {@link PlaceholderValueFormatter#getFormatName format name}.
     */
    public EscapingPlaceholderValueFormatter(final String formatName) {
        super(formatName);
    }

}
