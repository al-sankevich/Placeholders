package al.sankevich.placeholders.engines.format;

import al.sankevich.placeholders.configs.configurations.ContentTypeFormattingConfiguration;
import al.sankevich.placeholders.configs.configurations.ContentTypeProcessingConfiguration;
import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.formatters.impl.disabled.DisabledPlaceholderValueFormatter;
import al.sankevich.placeholders.formatters.impl.enabled.EnabledPlaceholderValueFormatter;

/**
 * Implementations of this interface are responsible for formatting {@link Placeholder placeholder}'s replacement value
 * with {@link EnabledPlaceholderValueFormatter enabled by default formatters}
 * from {@link ContentTypeFormattingConfiguration config} and
 * {@link DisabledPlaceholderValueFormatter disabled be default formatters}
 * from {@link Placeholder#getEnabledFormats  placeholder}.
 */
public interface PlaceholderValueFormattingEngine {

    /**
     * Formats {@link T placeholder}'s replacement value and maps it to {@link String} representation.
     *
     * @param placeholder the {@link T placeholder}, which replacement value will be formatted.
     * @param config      the {@link ContentTypeProcessingConfiguration}.
     * @param value       the {@link T placeholder}'s replacement value.
     * @param <T>         the expected placeholder type.
     * @return {@link String string} representation of formatted {@link T placeholder}'s replacement value.
     */
    <T extends Placeholder> String formatString(
            T placeholder,
            ContentTypeProcessingConfiguration<T> config,
            Object value
    );

    /**
     * Formats {@link T placeholder}'s replacement value and returns as any object.
     *
     * @param placeholder the {@link T placeholder}, which replacement value will be formatted.
     * @param config      the {@link ContentTypeProcessingConfiguration}.
     * @param value       the {@link T placeholder}'s replacement value.
     * @param <T>         the expected placeholder type.
     * @return formatted {@link T placeholder}'s replacement value.
     */
    <T extends Placeholder> Object formatObject(
            T placeholder,
            ContentTypeProcessingConfiguration<T> config,
            Object value
    );

}
