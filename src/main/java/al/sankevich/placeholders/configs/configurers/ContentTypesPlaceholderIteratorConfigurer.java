package al.sankevich.placeholders.configs.configurers;

import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.iterators.editable.EditablePlaceholdersIterator;

import java.util.function.Function;

public interface ContentTypesPlaceholderIteratorConfigurer<T extends Placeholder> {

    ContentTypesPlaceholderIteratorConfigurer<T> factory(Function<String, EditablePlaceholdersIterator<T>> f);

}
