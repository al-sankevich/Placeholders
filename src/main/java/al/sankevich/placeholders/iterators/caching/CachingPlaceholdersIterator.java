package al.sankevich.placeholders.iterators.caching;

import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.iterators.PlaceholdersIterator;

public interface CachingPlaceholdersIterator<T extends Placeholder> extends PlaceholdersIterator<T> {

    void add(T placeholder);

    boolean isCached();

}
