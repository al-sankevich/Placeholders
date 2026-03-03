package al.sankevich.placeholders.iterators.editable.impl.ext.wrappable.ext;

import al.sankevich.placeholders.contenttypes.ContentType;
import al.sankevich.placeholders.dtos.WrappablePlaceholder;
import al.sankevich.placeholders.iterators.editable.impl.ext.wrappable.WrappablePlaceholdersIterator;

/**
 * {@link WrappablePlaceholdersIterator}'s implementation for supporting
 * {@link WrappablePlaceholder placeholders} in {@code JSON} {@link ContentType content type}.
 */
public class JsonPlaceholdersIterator extends WrappablePlaceholdersIterator {

    public JsonPlaceholdersIterator(final String source) {
        super(source, '"');
    }

}
