package al.sankevich.placeholders.iterators.editable.impl.ext.decorator.ext;

import al.sankevich.placeholders.cache.PlaceholdersCache;
import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.engines.string.StringEngine;
import al.sankevich.placeholders.engines.string.impl.DefaultStringEngine;
import al.sankevich.placeholders.iterators.editable.EditablePlaceholdersIterator;
import al.sankevich.placeholders.iterators.editable.impl.ext.decorator.EditablePlaceholdersIteratorDecorator;

import java.util.function.Function;

/**
 * {@link EditablePlaceholdersIteratorDecorator}'s implementation
 * for supporting {@link PlaceholdersCache caching} during processing {@code source}.
 *
 * @param <T> the expected placeholder type.
 */
public class CachingPlaceholdersIteratorDecorator<T extends Placeholder> extends EditablePlaceholdersIteratorDecorator<T> {

    private final StringEngine buffer;
    private final int hash;
    private final boolean isCached;

    private int pointer = 0;
    private T current;

    /**
     * Builds {@link CachingPlaceholdersIteratorDecorator} with provided {@code source}
     * and {@link EditablePlaceholdersIteratorDecorator#wrappee wrapee} generating {@link Function function}
     * for its lazy initializing. Lazy initialization was used because if content is already cached,
     * we don't need wrappee, and it saves us time and memory (-50% both).
     *
     * @param source  the {@link String}, where {@link Placeholder placeholders} should be replaced.
     * @param wrappee the {@link EditablePlaceholdersIteratorDecorator wrapee} generating {@link Function function}.
     */
    public CachingPlaceholdersIteratorDecorator(
            final String source,
            final Function<String, EditablePlaceholdersIterator<T>> wrappee
    ) {
        super(PlaceholdersCache.isCached(source.hashCode()) ? null : wrappee.apply(source));
        this.hash = source.hashCode();
        this.isCached = PlaceholdersCache.isCached(this.hash);
        this.buffer = this.isCached ? new DefaultStringEngine(source) : null;
    }

    /**
     * {@inheritDoc}
     *
     * @return the {@link T placeholder} from {@link PlaceholdersCache cache} if the {@code source} already cached,
     * otherwise the {@link T placeholder}, obtained from {@link EditablePlaceholdersIteratorDecorator#wrappee wrapee}.
     */
    @Override
    public T get() {
        if (!isCached) {
            return super.get();
        }
        return current;
    }

    /**
     * {@inheritDoc}
     * If the {@code source} already cached, replaces {@link T placeholder} from {@link PlaceholdersCache cache},
     * otherwise invokes {@link EditablePlaceholdersIteratorDecorator#wrappee wrapee} method.
     */
    @Override
    public void replace(final String value) {
        if (!isCached) {
            super.replace(value);
        } else {
            if (current != null) {
                buffer.replace(current.getPosition(), value);
            }
        }
    }

    @Override
    public String result() {
        if (!isCached) {
            return super.result();
        }
        return buffer.toString();
    }

    /**
     * {@inheritDoc}
     * If the {@code source} already cached, checks for next {@link T placeholder} in {@link PlaceholdersCache cache},
     * otherwise invokes {@link EditablePlaceholdersIteratorDecorator#wrappee wrapee} method.
     *
     * @return {@code true} if next {@link T placeholder} exists, otherwise {@code false}.
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean hasNext() {
        if (!isCached) {
            boolean hasNext = super.hasNext();
            if (hasNext) {
                PlaceholdersCache.add(hash, super.get());
            }
            return hasNext;
        }
        boolean hasCurrent = (current = (T) PlaceholdersCache.get(hash, pointer)) != null;
        if (hasCurrent) {
            ++pointer;
        }
        return hasCurrent;
    }

}
