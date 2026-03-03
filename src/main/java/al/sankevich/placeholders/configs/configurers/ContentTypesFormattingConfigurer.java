package al.sankevich.placeholders.configs.configurers;

import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.formatters.impl.disabled.DisabledPlaceholderValueFormatter;
import al.sankevich.placeholders.formatters.impl.enabled.EnabledPlaceholderValueFormatter;
import al.sankevich.placeholders.mappers.ValueMapper;

import java.util.function.Consumer;

public interface ContentTypesFormattingConfigurer<T extends Placeholder> {

    ContentTypesFormattingConfigurer<T> enabled(Consumer<ContentTypesFormattersConfigurer<T, EnabledPlaceholderValueFormatter<? super T>>> c);

    ContentTypesFormattingConfigurer<T> disabled(Consumer<ContentTypesFormattersConfigurer<T, DisabledPlaceholderValueFormatter<? super T>>> c);

    ContentTypesFormattingConfigurer<T> valueMapper(ValueMapper vm);

}
