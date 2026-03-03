package al.sankevich.placeholders.iterators.editable.impl.ext.decorator;

import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.iterators.editable.EditablePlaceholdersIterator;

/**
 * {@link EditablePlaceholdersIterator}'s implementation for supporting {@code decorator} pattern.
 *
 * @param <T> the expected placeholder type.
 */
public abstract class EditablePlaceholdersIteratorDecorator<T extends Placeholder> implements EditablePlaceholdersIterator<T> {

    private final EditablePlaceholdersIterator<T> wrappee;

    public EditablePlaceholdersIteratorDecorator(final EditablePlaceholdersIterator<T> wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public T get() {
        return wrappee.get();
    }

    @Override
    public void replace(final String value) {
        wrappee.replace(value);
    }

    @Override
    public String result() {
        return wrappee.result();
    }

    @Override
    public boolean hasNext() {
        return wrappee.hasNext();
    }
}
