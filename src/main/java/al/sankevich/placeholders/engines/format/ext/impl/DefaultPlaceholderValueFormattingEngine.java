package al.sankevich.placeholders.engines.format.ext.impl;

import al.sankevich.placeholders.configs.configurations.ContentTypeProcessingConfiguration;
import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.engines.format.PlaceholderValueFormattingEngine;
import al.sankevich.placeholders.engines.format.ext.AbstractPlaceholderValueFormattingEngine;
import al.sankevich.placeholders.engines.string.StringEngine;
import al.sankevich.placeholders.engines.string.impl.DefaultStringEngine;
import al.sankevich.placeholders.formatters.PlaceholderValueFormatter;
import al.sankevich.placeholders.mappers.ValueMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static al.sankevich.placeholders.constants.ProcessingConstants.NULL;

/**
 * Default implementation of {@link PlaceholderValueFormattingEngine} for main methods.
 */
@RequiredArgsConstructor
public class DefaultPlaceholderValueFormattingEngine extends AbstractPlaceholderValueFormattingEngine {

    @Override
    public <T extends Placeholder> String formatString(
            final T placeholder,
            final ContentTypeProcessingConfiguration<T> config,
            final Object value
    ) {
        String valueString = NULL;

        if (Objects.nonNull(value)) {
            valueString = applyFormats(placeholder, config, value);
        }

        return valueString;
    }

    private <T extends Placeholder> String applyFormats(
            final T placeholder,
            final ContentTypeProcessingConfiguration<T> config,
            final Object value
    ) {
        ValueMapper mapper = config.valueMapper();

        StringEngine buffer = new DefaultStringEngine(mapper.stringify(value));

        applyDisabledByDefaultFormats(placeholder, config.disabledFormatters(), buffer);
        applyEnabledByDefaultFormats(placeholder, config.enabledFormatters(), buffer);

        return buffer.toString();
    }

    private <T extends Placeholder> void applyDisabledByDefaultFormats(
            final T placeholder,
            final Map<String, PlaceholderValueFormatter<? super T>> formatters,
            final StringEngine buffer
    ) {
        for (String[] format : placeholder.getEnabledFormats()) {

            PlaceholderValueFormatter<? super T> formatter = formatters.get(format[0]);

            if (Objects.nonNull(formatter)) {
                formatter.format(placeholder, format[1], buffer);
            }
        }
    }

    private <T extends Placeholder> void applyEnabledByDefaultFormats(
            final T placeholder,
            final List<PlaceholderValueFormatter<? super T>> formatters,
            final StringEngine buffer
    ) {
        Set<String> disabledFormats = placeholder.getDisabledFormats();

        for (PlaceholderValueFormatter<? super T> formatter : formatters) {
            if (!disabledFormats.contains(formatter.getFormatName())) {
                formatter.format(placeholder, null, buffer);
            }
        }
    }
}
