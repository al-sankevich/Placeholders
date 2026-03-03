package al.sankevich.placeholders.engines.format.ext;

import al.sankevich.placeholders.configs.configurations.ContentTypeProcessingConfiguration;
import al.sankevich.placeholders.constants.FormatingConstants;
import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.engines.format.PlaceholderValueFormattingEngine;

import java.util.Set;

/**
 * General implementation of {@link PlaceholderValueFormattingEngine} for some methods.
 */
public abstract class AbstractPlaceholderValueFormattingEngine implements PlaceholderValueFormattingEngine {

    @Override
    public <T extends Placeholder> Object formatObject(
            final T placeholder,
            final ContentTypeProcessingConfiguration<T> config,
            final Object value
    ) {
        Set<String> disabledFormats = placeholder.getDisabledFormats();

        if (placeholder.getEnabledFormats().isEmpty() &&
                disabledFormats.size() == 1 && disabledFormats.contains(FormatingConstants.NO_WRAP)) {
            return value;
        }

        return formatString(placeholder, config, value);
    }

}
