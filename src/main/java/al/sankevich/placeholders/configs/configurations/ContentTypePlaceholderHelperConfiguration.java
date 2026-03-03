package al.sankevich.placeholders.configs.configurations;

import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.iterators.editable.EditablePlaceholdersIterator;
import lombok.Getter;

import java.util.function.Function;

@Getter
public class ContentTypePlaceholderHelperConfiguration<T extends Placeholder> {

    private Function<String, EditablePlaceholdersIterator<T>> f;

    public void set(final Function<String, EditablePlaceholdersIterator<T>> f) {
        this.f = f;
    }

}
