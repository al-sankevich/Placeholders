package al.sankevich.placeholders.configs.configurations;

import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.formatters.PlaceholderValueFormatter;
import al.sankevich.placeholders.iterators.editable.EditablePlaceholdersIterator;
import al.sankevich.placeholders.mappers.ValueMapper;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Getter
public class ContentTypeProcessingConfiguration<T extends Placeholder> {

    private final ContentTypeFormattingConfiguration<T> ctfc = new ContentTypeFormattingConfiguration<>();
    private final ContentTypePlaceholderHelperConfiguration<T> ctphc = new ContentTypePlaceholderHelperConfiguration<>();

    public void add(final PlaceholderValueFormatter<? super T> pvf) {
        ctfc.add(pvf);
    }

    public void iteratorFactory(final Function<String, EditablePlaceholdersIterator<T>> f) {
        ctphc.set(f);
    }

    public void setValueMapper(final ValueMapper vm) {
        ctfc.setValueMapper(vm);
    }

    public List<PlaceholderValueFormatter<? super T>> enabledFormatters() {
        return ctfc.getEnabledFormatters();
    }

    public Map<String, PlaceholderValueFormatter<? super T>> disabledFormatters() {
        return ctfc.getDisabledFormatters();
    }

    public Function<String, EditablePlaceholdersIterator<T>> iteratorFactory() {
        return ctphc.getF();
    }

    public ValueMapper valueMapper() {
        return ctfc.getValueMapper();
    }

}
