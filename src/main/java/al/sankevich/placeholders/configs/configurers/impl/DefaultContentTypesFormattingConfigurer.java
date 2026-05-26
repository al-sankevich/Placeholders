package al.sankevich.placeholders.configs.configurers.impl;

import al.sankevich.placeholders.configs.configurations.ContentTypesProcessingConfiguration;
import al.sankevich.placeholders.configs.configurers.ContentTypesFormattersConfigurer;
import al.sankevich.placeholders.configs.configurers.ContentTypesFormattingConfigurer;
import al.sankevich.placeholders.configs.configurers.auto.BiApplicatorConfigurer;
import al.sankevich.placeholders.configs.configurers.auto.Applicator;
import al.sankevich.placeholders.contenttypes.ContentType;
import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.formatters.impl.disabled.DisabledPlaceholderValueFormatter;
import al.sankevich.placeholders.formatters.impl.enabled.EnabledPlaceholderValueFormatter;
import al.sankevich.placeholders.mappers.ValueMapper;

public class DefaultContentTypesFormattingConfigurer<T extends Placeholder>
        extends BiApplicatorConfigurer<ContentTypesProcessingConfiguration, ContentType[]>
        implements ContentTypesFormattingConfigurer<T> {

    public DefaultContentTypesFormattingConfigurer(
            final ContentTypesProcessingConfiguration ctpc,
            final ContentType[] cts
    ) {
        super(ctpc, cts);
    }

    @Override
    public DefaultContentTypesFormattingConfigurer<T> enabled(
            final Applicator<ContentTypesFormattersConfigurer<T, EnabledPlaceholderValueFormatter<? super T>>> c
    ) {
        addApplicator(
                "disabledContentTypesFormatters", (ctpc, cts) ->
                        c.apply(new DefaultContentTypesFormattersConfigurer<>(ctpc, cts))
        );
        return this;
    }

    @Override
    public DefaultContentTypesFormattingConfigurer<T> disabled(
            final Applicator<ContentTypesFormattersConfigurer<T, DisabledPlaceholderValueFormatter<? super T>>> c
    ) {
        addApplicator(
                "enabledContentTypesFormatters", (ctpc, cts) ->
                        c.apply(new DefaultContentTypesFormattersConfigurer<>(ctpc, cts))
        );
        return this;
    }

    @Override
    public DefaultContentTypesFormattingConfigurer<T> valueMapper(final ValueMapper vm) {
        addConsumer("valueMapper", (ctpc, cts) -> {
            for (ContentType ct : cts) {
                ctpc.<T>setValueMapper(ct, vm);
            }
        });
        return this;
    }

}
