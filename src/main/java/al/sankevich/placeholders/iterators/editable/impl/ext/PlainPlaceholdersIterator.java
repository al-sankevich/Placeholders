package al.sankevich.placeholders.iterators.editable.impl.ext;


import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.iterators.editable.impl.AbstractEditablePlaceholdersIterator;
import al.sankevich.placeholders.parsers.PlaceholderParser;
import al.sankevich.placeholders.parsers.impl.ext.PlainPlaceholdersParser;

import java.util.function.Function;

/**
 * {@link AbstractEditablePlaceholdersIterator}'s implementation
 * for iterating through placeholders of {@link Placeholder} type.
 */
public class PlainPlaceholdersIterator extends AbstractEditablePlaceholdersIterator<Placeholder> {

    /**
     * Builds default {@link PlainPlaceholdersIterator} with {@link PlainPlaceholdersParser default} parser.
     *
     * @param source the {@link String}, where {@link Placeholder placeholders} should be replaced.
     */
    public PlainPlaceholdersIterator(final String source) {
        super(source, PlainPlaceholdersParser::new);
    }

    /**
     * Builds {@link PlainPlaceholdersIterator} with custom
     * {@link PlaceholderParser parser} generating {@link Function function}.
     *
     * @param source the {@link String}, where {@link Placeholder placeholders} should be replaced.
     * @param parser the custom {@link Placeholder placeholders} {@link PlaceholderParser parser}
     *               generating {@link Function function}.
     */
    public PlainPlaceholdersIterator(
            final String source,
            final Function<String, PlaceholderParser<Placeholder>> parser
    ) {
        super(source, parser);
    }
}
