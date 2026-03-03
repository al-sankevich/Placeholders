package al.sankevich.placeholders.configs.configurers.impl;

import al.sankevich.placeholders.configs.configurations.ContentTypesProcessingConfiguration;
import al.sankevich.placeholders.configs.configurers.ContentTypesFormattersConfigurer;
import al.sankevich.placeholders.configs.configurers.ContentTypesFormattingConfigurer;
import al.sankevich.placeholders.contenttypes.ContentType;
import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.formatters.impl.disabled.DisabledPlaceholderValueFormatter;
import al.sankevich.placeholders.formatters.impl.enabled.EnabledPlaceholderValueFormatter;
import al.sankevich.placeholders.mappers.ValueMapper;
import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;

@RequiredArgsConstructor
public class DefaultContentTypesFormattingConfigurer<T extends Placeholder> implements ContentTypesFormattingConfigurer<T> {

    private final ContentTypesProcessingConfiguration ctpc;
    private final ContentType[] cts;

    @Override
    public DefaultContentTypesFormattingConfigurer<T> enabled(final Consumer<ContentTypesFormattersConfigurer<T, EnabledPlaceholderValueFormatter<? super T>>> c) {
        c.accept(new DefaultContentTypesFormattersConfigurer<>(ctpc, cts));
        return this;
    }

    @Override
    public DefaultContentTypesFormattingConfigurer<T> disabled(final Consumer<ContentTypesFormattersConfigurer<T, DisabledPlaceholderValueFormatter<? super T>>> c) {
        c.accept(new DefaultContentTypesFormattersConfigurer<>(ctpc, cts));
        return this;
    }

    @Override
    public DefaultContentTypesFormattingConfigurer<T> valueMapper(final ValueMapper vm) {
        for (ContentType ct : cts) {
            ctpc.<T>setValueMapper(ct, vm);
        }

        return this;
    }

}
