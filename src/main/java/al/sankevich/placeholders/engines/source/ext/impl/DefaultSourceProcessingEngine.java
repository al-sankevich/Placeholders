package al.sankevich.placeholders.engines.source.ext.impl;

import al.sankevich.placeholders.configs.configurations.PlaceholdersProcessingConfiguration;
import al.sankevich.placeholders.contenttypes.ContentType;
import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.engines.format.PlaceholderValueFormattingEngine;
import al.sankevich.placeholders.engines.format.ext.impl.DefaultPlaceholderValueFormattingEngine;
import al.sankevich.placeholders.engines.source.SourceProcessingEngine;
import al.sankevich.placeholders.engines.source.ext.AbstractSourceProcessingEngine;
import al.sankevich.placeholders.iterators.editable.EditablePlaceholdersIterator;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import static al.sankevich.placeholders.contenttypes.impl.DefaultContentTypes.PLAIN;

/**
 * Default implementation of {@link SourceProcessingEngine} for main methods.
 */
@RequiredArgsConstructor
public class DefaultSourceProcessingEngine extends AbstractSourceProcessingEngine {

    private final PlaceholdersProcessingConfiguration config;

    private final PlaceholderValueFormattingEngine formattingEngine;

    public DefaultSourceProcessingEngine(final PlaceholdersProcessingConfiguration config) {
        this(config, new DefaultPlaceholderValueFormattingEngine());
    }

    @Override
    public String process(
            final String source,
            final ContentType type,
            final Function<String, Object> valuesProvider,
            final String... skippablePlaceholders
    ) {
        EditablePlaceholdersIterator<?> iterator = getIterator(source, type);
        Set<String> skippablePlaceholdersSet = getSkippablePlaceholders(skippablePlaceholders);

        while (iterator.hasNext()) {

            Placeholder placeholder = iterator.get();

            processString(skippablePlaceholdersSet, iterator, placeholder, valuesProvider, type);
        }

        return iterator.result();
    }

    private EditablePlaceholdersIterator<?> getIterator(final String source, final ContentType type) {
        return config.getIteratorFactory(type).apply(source);
    }

    private Set<String> getSkippablePlaceholders(final String... skippablePlaceholders) {
        Set<String> skippablePlaceholdersSet = new HashSet<>(config.getSkippablePlaceholders());
        skippablePlaceholdersSet.addAll(Set.of(skippablePlaceholders));
        return skippablePlaceholdersSet;
    }

    @Override
    protected Object process(
            final String source,
            final Function<String, Object> valuesProvider,
            final String... skippablePlaceholders
    ) {
        EditablePlaceholdersIterator<?> iterator = getIterator(source, PLAIN);
        Set<String> skippablePlaceholdersSet = getSkippablePlaceholders(skippablePlaceholders);

        while (iterator.hasNext()) {

            Placeholder placeholder = iterator.get();

            if (placeholder.getPosition().length() == source.length()) {
                return processObject(skippablePlaceholdersSet, placeholder, valuesProvider, source);
            } else {
                processString(skippablePlaceholdersSet, iterator, placeholder, valuesProvider, PLAIN);
            }
        }

        return iterator.result();
    }

    private void processString(
            final Set<String> skippablePlaceholdersSet,
            final EditablePlaceholdersIterator<?> iterator,
            final Placeholder placeholder,
            final Function<String, Object> valuesProvider,
            final ContentType type
    ) {
        String placeholderName = placeholder.getName();
        if (!skippablePlaceholdersSet.contains(placeholderName)) {
            Object value = valuesProvider.apply(placeholderName);
            String valueString = formattingEngine.formatString(
                    placeholder,
                    config.getContentTypeProcessingConfig(type),
                    value
            );

            iterator.replace(valueString);
        }
    }

    private Object processObject(
            final Set<String> skippablePlaceholdersSet,
            final Placeholder placeholder,
            final Function<String, Object> valuesProvider,
            final String source
    ) {
        String placeholderName = placeholder.getName();
        if (!skippablePlaceholdersSet.contains(placeholderName)) {
            Object value = valuesProvider.apply(placeholderName);

            return formattingEngine.formatObject(
                    placeholder,
                    config.getContentTypeProcessingConfig(PLAIN),
                    value
            );
        }

        return source;
    }

}
