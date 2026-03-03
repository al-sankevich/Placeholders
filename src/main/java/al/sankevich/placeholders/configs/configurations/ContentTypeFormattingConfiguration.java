package al.sankevich.placeholders.configs.configurations;

import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.formatters.PlaceholderValueFormatter;
import al.sankevich.placeholders.mappers.ValueMapper;
import al.sankevich.placeholders.mappers.impl.DefaultValueMapper;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class ContentTypeFormattingConfiguration<T extends Placeholder> {

    private final Map<String, PlaceholderValueFormatter<? super T>> disabledFormatters = new HashMap<>();
    private final List<PlaceholderValueFormatter<? super T>> enabledFormatters = new ArrayList<>();

    private ValueMapper vm = new DefaultValueMapper();

    public void add(final PlaceholderValueFormatter<? super T> pvf) {
        if (pvf.isEnabledByDefault()) {
            enabledFormatters.add(pvf);
        } else {
            disabledFormatters.put(pvf.getFormatName(), pvf);
        }
    }

    public ValueMapper getValueMapper() {
        return vm;
    }

    public void setValueMapper(final ValueMapper vm) {
        this.vm = vm;
    }
}
