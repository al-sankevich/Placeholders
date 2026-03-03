package al.sankevich.placeholders.configs.configurers.impl;

import al.sankevich.placeholders.configs.configurations.ContentTypesProcessingConfiguration;
import al.sankevich.placeholders.configs.configurers.ContentTypesPlaceholderIteratorConfigurer;
import al.sankevich.placeholders.contenttypes.ContentType;
import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.iterators.editable.EditablePlaceholdersIterator;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@RequiredArgsConstructor
public class DefaultContentTypesPlaceholderIteratorConfigurer<T extends Placeholder> implements ContentTypesPlaceholderIteratorConfigurer<T> {

    private final ContentTypesProcessingConfiguration ctpc;
    private final ContentType[] cts;

    @Override
    public DefaultContentTypesPlaceholderIteratorConfigurer<T> factory(final Function<String, EditablePlaceholdersIterator<T>> f) {
        for (ContentType type : cts) {
            ctpc.iteratorFactory(type, f);
        }
        return this;
    }

}
