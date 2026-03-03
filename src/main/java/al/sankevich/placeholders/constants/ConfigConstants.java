package al.sankevich.placeholders.constants;

import al.sankevich.placeholders.configs.configurations.PlaceholdersProcessingConfiguration;
import al.sankevich.placeholders.configs.configurers.impl.DefaultPlaceholdersProcessingConfigurationConfigurer;
import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.dtos.WrappablePlaceholder;
import al.sankevich.placeholders.formatters.impl.enabled.ext.WrappingPlaceholderValueFormatter;
import al.sankevich.placeholders.formatters.impl.enabled.ext.escaping.ext.JsonEscapingPlaceholderValueFormatter;
import al.sankevich.placeholders.formatters.impl.enabled.ext.escaping.ext.SqlEscapingPlaceholderValueFormatter;
import al.sankevich.placeholders.iterators.editable.EditablePlaceholdersIterator;
import al.sankevich.placeholders.iterators.editable.impl.ext.PlainPlaceholdersIterator;
import al.sankevich.placeholders.iterators.editable.impl.ext.decorator.ext.CachingPlaceholdersIteratorDecorator;
import al.sankevich.placeholders.iterators.editable.impl.ext.wrappable.ext.JsonPlaceholdersIterator;
import al.sankevich.placeholders.iterators.editable.impl.ext.wrappable.ext.SqlPlaceholdersIterator;

import java.util.function.Function;

import static al.sankevich.placeholders.constants.ProcessingConstants.JSON_UNIT_ANY_NUMBER;
import static al.sankevich.placeholders.constants.ProcessingConstants.JSON_UNIT_ANY_STRING;
import static al.sankevich.placeholders.contenttypes.impl.DefaultContentTypes.JSON;
import static al.sankevich.placeholders.contenttypes.impl.DefaultContentTypes.PLAIN;
import static al.sankevich.placeholders.contenttypes.impl.DefaultContentTypes.SQL;

/**
 * Contains constants for {@code configuration} needs.
 */
public class ConfigConstants {

    public static final PlaceholdersProcessingConfiguration DEFAULT_PLACEHOLDERS_PROCESSING_CONFIGURATION
            = DefaultPlaceholdersProcessingConfigurationConfigurer.init()
            .contentTypes(
                    config -> config.forTypes(PLAIN)
                            .iterator(c -> c.factory(PlainPlaceholdersIterator::new))
                            .<WrappablePlaceholder>forTypes(JSON)
                            .iterator(c -> c
                                    .factory(cacheDecorator(JsonPlaceholdersIterator::new)))
                            .formatting(c -> c
                                    .enabled(c1 -> c1
                                            .addFormatter(new JsonEscapingPlaceholderValueFormatter())
                                    )
                            )
                            .<WrappablePlaceholder>forTypes(SQL)
                            .iterator(c -> c
                                    .factory(cacheDecorator(SqlPlaceholdersIterator::new)))
                            .formatting(c -> c
                                    .enabled(c1 -> c1
                                            .addFormatter(new SqlEscapingPlaceholderValueFormatter())
                                    )
                            )
                            .<WrappablePlaceholder>forTypes(JSON, SQL)
                            .formatting(c -> c
                                    .enabled(c1 -> c1
                                            .addFormatter(new WrappingPlaceholderValueFormatter())
                                    )
                            )
            )
            .placeholders(placeholders -> placeholders.skip(JSON_UNIT_ANY_STRING, JSON_UNIT_ANY_NUMBER))
            .get();

    private static <T extends Placeholder> Function<String, EditablePlaceholdersIterator<T>> cacheDecorator(
            Function<String, EditablePlaceholdersIterator<T>> iterator
    ) {
        return (s) -> new CachingPlaceholdersIteratorDecorator<>(s, iterator);
    }

/*    private static <T extends Placeholder> Function<String, EditablePlaceholdersIterator<T>> cacheDecorator(
            Function<String, EditablePlaceholdersIterator<T>> iteratorFactory
    ) {
        return iteratorFactory;
    }*/

}
