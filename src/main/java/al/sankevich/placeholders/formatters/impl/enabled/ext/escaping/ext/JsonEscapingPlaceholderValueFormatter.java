package al.sankevich.placeholders.formatters.impl.enabled.ext.escaping.ext;

import al.sankevich.placeholders.contenttypes.ContentType;
import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.engines.string.StringEngine;
import al.sankevich.placeholders.formatters.PlaceholderValueFormatter;
import al.sankevich.placeholders.formatters.impl.enabled.ext.escaping.EscapingPlaceholderValueFormatter;
import al.sankevich.placeholders.utils.FormatUtils;
import lombok.NoArgsConstructor;

/**
 * {@link EscapingPlaceholderValueFormatter}'s implementation
 * for supporting {@code JSON} {@link ContentType content type}.
 */
@NoArgsConstructor
public class JsonEscapingPlaceholderValueFormatter extends EscapingPlaceholderValueFormatter {

    /**
     * Builds {@code JsonEscapingPlaceholderValueFormatter} with custom
     * disabling {@link PlaceholderValueFormatter#getFormatName format name}.
     *
     * @param formatName the custom disabling {@link PlaceholderValueFormatter#getFormatName format name}.
     */
    public JsonEscapingPlaceholderValueFormatter(final String formatName) {
        super(formatName);
    }

    @Override
    public void format(final Placeholder placeholder, final String formatValue, final StringEngine buffer) {
        FormatUtils.quoteJson(buffer);
    }

}
