package al.sankevich.placeholders.iterators.editable.impl.ext.wrappable;

import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.dtos.WrappablePlaceholder;
import al.sankevich.placeholders.iterators.editable.impl.AbstractEditablePlaceholdersIterator;
import al.sankevich.placeholders.parsers.PlaceholderParser;
import al.sankevich.placeholders.parsers.impl.ext.WrappablePlaceholdersParser;

import java.util.function.Function;

/**
 * General {@link AbstractEditablePlaceholdersIterator}'s implementation
 * for iterating through placeholders of {@link WrappablePlaceholder} type.
 */
public abstract class WrappablePlaceholdersIterator extends AbstractEditablePlaceholdersIterator<WrappablePlaceholder> {

    /**
     * Builds default {@link WrappablePlaceholdersIterator} with {@link WrappablePlaceholdersParser default} parser.
     *
     * @param source    the {@link String}, where {@link Placeholder placeholders} should be replaced.
     * @param delimiter the delimiter of {@link WrappablePlaceholder placeholder}.
     */
    public WrappablePlaceholdersIterator(final String source, final char delimiter) {
        super(source, (s) -> new WrappablePlaceholdersParser(s, delimiter));
    }

    /**
     * Builds {@link WrappablePlaceholdersIterator} with custom
     * {@link PlaceholderParser parser} generating {@link Function function}.
     *
     * @param source the {@link String}, where {@link Placeholder placeholders} should be replaced.
     * @param parser the custom {@link WrappablePlaceholder placeholders} {@link PlaceholderParser parser}
     *               generating {@link Function function}.
     */
    public WrappablePlaceholdersIterator(
            final String source,
            final Function<String, PlaceholderParser<WrappablePlaceholder>> parser
    ) {
        super(source, parser);
    }
}
