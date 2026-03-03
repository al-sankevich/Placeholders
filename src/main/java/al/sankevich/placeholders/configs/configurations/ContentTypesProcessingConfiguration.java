package al.sankevich.placeholders.configs.configurations;

import al.sankevich.placeholders.contenttypes.ContentType;
import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.formatters.PlaceholderValueFormatter;
import al.sankevich.placeholders.iterators.editable.EditablePlaceholdersIterator;
import al.sankevich.placeholders.mappers.ValueMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ContentTypesProcessingConfiguration {

    private final Map<ContentType, ContentTypeProcessingConfiguration<?>> configs = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T extends Placeholder> ContentTypeProcessingConfiguration<T> get(final ContentType ct) {
        return (ContentTypeProcessingConfiguration<T>)
                configs.computeIfAbsent(ct, k -> new ContentTypeProcessingConfiguration<T>());
    }

    public <T extends Placeholder> void addFormatter(
            final ContentType ct,
            final PlaceholderValueFormatter<? super T> pvf
    ) {
        this.<T>get(ct).add(pvf);
    }

    public <T extends Placeholder> void iteratorFactory(
            final ContentType ct,
            final Function<String, EditablePlaceholdersIterator<T>> f
    ) {
        this.<T>get(ct).iteratorFactory(f);
    }

    public <T extends Placeholder> void setValueMapper(final ContentType ct, final ValueMapper vm) {
        this.<T>get(ct).setValueMapper(vm);
    }

}
