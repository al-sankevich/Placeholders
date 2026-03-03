package al.sankevich.placeholders.iterators.editable.impl;

import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.engines.string.StringEngine;
import al.sankevich.placeholders.engines.string.impl.DefaultStringEngine;
import al.sankevich.placeholders.iterators.editable.EditablePlaceholdersIterator;
import al.sankevich.placeholders.parsers.PlaceholderParser;

import java.util.function.Function;

/**
 * General implementation of {@link EditablePlaceholdersIterator}.
 *
 * @param <T> the expected placeholder type.
 */
public abstract class AbstractEditablePlaceholdersIterator<T extends Placeholder> implements EditablePlaceholdersIterator<T> {

    protected final PlaceholderParser<T> parser;
    private final StringEngine buffer;
    private T current;

    /**
     * @param source the {@link String}, where {@link T placeholders} should be replaced.
     * @param parser the {@link T placeholders} {@link PlaceholderParser parser} generating {@link Function function}.
     */
    public AbstractEditablePlaceholdersIterator(
            final String source,
            final Function<String, PlaceholderParser<T>> parser
    ) {
        this.parser = parser.apply(source);
        this.buffer = new DefaultStringEngine(source);
    }

    @Override
    public boolean hasNext() {
        return (current = parser.parse()) != null;
    }

    @Override
    public T get() {
        return current;
    }

    @Override
    public void replace(final String value) {
        if (current != null) {
            buffer.replace(current.getPosition(), value);
        }
    }

    @Override
    public String result() {
        return buffer.toString();
    }

}
