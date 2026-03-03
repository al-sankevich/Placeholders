package al.sankevich.placeholders.iterators.caching.impl;

import al.sankevich.placeholders.cache.PlaceholdersCache;
import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.iterators.caching.CachingPlaceholdersIterator;

public class DefaultCachingPlaceholdersIterator<T extends Placeholder> implements CachingPlaceholdersIterator<T> {

    private final int hash;
    private final boolean isCached;

    private int pointer;
    private T current;

    public DefaultCachingPlaceholdersIterator(final String source) {
        this.hash = source.hashCode();
        this.isCached = PlaceholdersCache.isCached(this.hash);
    }

    @Override
    public T get() {
        return current;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean hasNext() {
        boolean hasCurrent = (current = (T) PlaceholdersCache.get(hash, pointer)) != null;
        if (hasCurrent) {
            ++pointer;
        }
        return hasCurrent;
    }

    @Override
    public void add(T placeholder) {
        PlaceholdersCache.add(hash, placeholder);
    }

    @Override
    public boolean isCached() {
        return isCached;
    }

}
