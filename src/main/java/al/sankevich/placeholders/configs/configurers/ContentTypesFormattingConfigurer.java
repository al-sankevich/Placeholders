package al.sankevich.placeholders.configs.configurers;

import al.sankevich.placeholders.configs.configurers.auto.AutoConfigurer;
import al.sankevich.placeholders.configs.configurers.auto.Applicator;
import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.formatters.impl.disabled.DisabledPlaceholderValueFormatter;
import al.sankevich.placeholders.formatters.impl.enabled.EnabledPlaceholderValueFormatter;
import al.sankevich.placeholders.mappers.ValueMapper;

public interface ContentTypesFormattingConfigurer<T extends Placeholder> extends AutoConfigurer {

    ContentTypesFormattingConfigurer<T> enabled(Applicator<ContentTypesFormattersConfigurer<T, EnabledPlaceholderValueFormatter<? super T>>> c);

    ContentTypesFormattingConfigurer<T> disabled(Applicator<ContentTypesFormattersConfigurer<T, DisabledPlaceholderValueFormatter<? super T>>> c);

    ContentTypesFormattingConfigurer<T> valueMapper(ValueMapper vm);

}
