package al.sankevich.placeholders.engines.source;

import al.sankevich.placeholders.contenttypes.ContentType;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Interface for processing sources with placeholders.
 * This interface defines the contract for processing content that contains
 * placeholders, replacing them with actual values based on provided functions.
 */
public interface SourceProcessingEngine {

    /**
     * Process a string source with placeholders.
     * Replaces all placeholders found in the {@code source} string with values provided by the {@code valuesProvider} function.
     *
     * @param source                The source string containing placeholders to be replaced
     * @param type                  The content type indicating how to process the placeholders
     * @param valuesProvider        Function that provides values for placeholders by name
     * @param skippablePlaceholders Names of placeholders that should be skipped during processing
     * @return The processed string with placeholders replaced by their values
     */
    String process(
            String source,
            ContentType type,
            Function<String, Object> valuesProvider,
            String... skippablePlaceholders
    );

    /**
     * Process a map source with placeholders.
     * Recursively processes all string values in the map, replacing placeholders with values.
     *
     * @param source                The source map containing placeholders to be replaced
     * @param valuesProvider        Function that provides values for placeholders by name
     * @param skippablePlaceholders Names of placeholders that should be skipped during processing
     * @param <K>                   Type of keys in the map
     * @param <V>                   Type of values in the map
     * @return The processed map with placeholders replaced by their values
     */
    <K, V> Map<K, V> process(
            Map<K, V> source,
            Function<String, Object> valuesProvider,
            String... skippablePlaceholders
    );

    /**
     * Process a list source with placeholders.
     * Recursively processes all string elements in the list, replacing placeholders with values.
     *
     * @param source                The source list containing placeholders to be replaced
     * @param valuesProvider        Function that provides values for placeholders by name
     * @param skippablePlaceholders Names of placeholders that should be skipped during processing
     * @param <T>                   Type of elements in the list
     * @return The processed list with placeholders replaced by their values
     */
    <T> List<T> process(List<T> source, Function<String, Object> valuesProvider, String... skippablePlaceholders);

}
