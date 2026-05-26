package al.sankevich.placeholders.configs.configurers.impl;

import al.sankevich.placeholders.configs.configurations.ContentTypesProcessingConfiguration;
import al.sankevich.placeholders.configs.configurers.ContentTypeConfigurer;
import al.sankevich.placeholders.configs.configurers.ContentTypesFormattingConfigurer;
import al.sankevich.placeholders.configs.configurers.ContentTypesPlaceholderIteratorConfigurer;
import al.sankevich.placeholders.configs.configurers.ContentTypesProcessingConfigurer;
import al.sankevich.placeholders.configs.configurers.auto.BiApplicatorConfigurer;
import al.sankevich.placeholders.configs.configurers.auto.Applicator;
import al.sankevich.placeholders.contenttypes.ContentType;
import al.sankevich.placeholders.dtos.Placeholder;
import lombok.Setter;

@Setter
public class DefaultContentTypesProcessingConfigurer<T extends Placeholder>
        extends BiApplicatorConfigurer<ContentTypesProcessingConfiguration, ContentType[]>
        implements ContentTypesProcessingConfigurer<T> {

    private final ContentTypeConfigurer ctc;

    public DefaultContentTypesProcessingConfigurer(
            final ContentTypeConfigurer ctc,
            final ContentTypesProcessingConfiguration ctpc,
            final ContentType[] cts
    ) {
        super(ctpc, cts);
        this.ctc = ctc;
    }

    @Override
    public DefaultContentTypesProcessingConfigurer<T> formatting(final Applicator<ContentTypesFormattingConfigurer<T>> c) {
        addApplicator(
                "contentTypesFormatting", (ctpc, cts) ->
                        c.apply(new DefaultContentTypesFormattingConfigurer<>(ctpc, cts))
        );
        return this;
    }

    @Override
    public DefaultContentTypesProcessingConfigurer<T> iterator(final Applicator<ContentTypesPlaceholderIteratorConfigurer<T>> c) {
        addApplicator(
                "contentTypesPlaceholderIterator", (ctpc, cts) ->
                        c.apply(new DefaultContentTypesPlaceholderIteratorConfigurer<>(ctpc, cts))
        );
        return this;
    }

    @Override
    public <N extends Placeholder> ContentTypesProcessingConfigurer<N> forTypes(final ContentType... cts) {
        return ctc.forTypes(cts);
    }
}
