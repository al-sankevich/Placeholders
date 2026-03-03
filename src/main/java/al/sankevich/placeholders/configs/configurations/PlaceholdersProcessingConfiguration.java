package al.sankevich.placeholders.configs.configurations;

import al.sankevich.placeholders.contenttypes.ContentType;
import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.iterators.editable.EditablePlaceholdersIterator;
import lombok.Getter;

import java.util.Set;
import java.util.function.Function;

@Getter
public class PlaceholdersProcessingConfiguration {

    private final ContentTypesProcessingConfiguration ctpc = new ContentTypesProcessingConfiguration();
    private final SkippablePlaceholdersConfiguration spc = new SkippablePlaceholdersConfiguration();

    public <T extends Placeholder> Function<String, EditablePlaceholdersIterator<T>> getIteratorFactory(
            final ContentType ct
    ) {
        return ctpc.<T>get(ct).iteratorFactory();
    }

    public Set<String> getSkippablePlaceholders() {
        return spc.get();
    }

    public <T extends Placeholder> ContentTypeProcessingConfiguration<T> getContentTypeProcessingConfig(
            final ContentType ct
    ) {
        return ctpc.get(ct);
    }

}
