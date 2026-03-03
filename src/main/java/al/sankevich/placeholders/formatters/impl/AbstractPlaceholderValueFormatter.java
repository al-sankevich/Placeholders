package al.sankevich.placeholders.formatters.impl;

import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.formatters.PlaceholderValueFormatter;
import lombok.RequiredArgsConstructor;

/**
 * General implementation of {@link PlaceholderValueFormatter}.
 *
 * @param <T> the expected placeholder type.
 */
@RequiredArgsConstructor
public abstract class AbstractPlaceholderValueFormatter<T extends Placeholder> implements PlaceholderValueFormatter<T> {

    private final boolean isApplicableByDefault;
    private final String formatName;

    @Override
    public boolean isEnabledByDefault() {
        return isApplicableByDefault;
    }

    @Override
    public String getFormatName() {
        return formatName;
    }

}
