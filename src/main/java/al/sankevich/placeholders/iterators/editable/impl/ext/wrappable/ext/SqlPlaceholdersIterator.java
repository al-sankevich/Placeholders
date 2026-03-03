package al.sankevich.placeholders.iterators.editable.impl.ext.wrappable.ext;

import al.sankevich.placeholders.contenttypes.ContentType;
import al.sankevich.placeholders.dtos.WrappablePlaceholder;
import al.sankevich.placeholders.iterators.editable.impl.ext.wrappable.WrappablePlaceholdersIterator;

/**
 * {@link WrappablePlaceholdersIterator}'s implementation for supporting
 * {@link WrappablePlaceholder placeholders} in {@code SQL} {@link ContentType content type}.
 */
public class SqlPlaceholdersIterator extends WrappablePlaceholdersIterator {

    public SqlPlaceholdersIterator(final String source) {
        super(source, '\'');
    }
}
