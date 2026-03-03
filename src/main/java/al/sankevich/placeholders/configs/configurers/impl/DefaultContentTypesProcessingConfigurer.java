package al.sankevich.placeholders.configs.configurers.impl;

import al.sankevich.placeholders.configs.configurations.ContentTypesProcessingConfiguration;
import al.sankevich.placeholders.configs.configurers.ContentTypeConfigurer;
import al.sankevich.placeholders.configs.configurers.ContentTypesFormattingConfigurer;
import al.sankevich.placeholders.configs.configurers.ContentTypesPlaceholderIteratorConfigurer;
import al.sankevich.placeholders.configs.configurers.ContentTypesProcessingConfigurer;
import al.sankevich.placeholders.contenttypes.ContentType;
import al.sankevich.placeholders.dtos.Placeholder;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.function.Consumer;

@Setter
@RequiredArgsConstructor
public class DefaultContentTypesProcessingConfigurer<T extends Placeholder>
        implements ContentTypesProcessingConfigurer<T> {

    private final ContentTypeConfigurer ctc;
    private final ContentTypesProcessingConfiguration ctpc;
    private final ContentType[] cts;

    @Override
    public DefaultContentTypesProcessingConfigurer<T> formatting(final Consumer<ContentTypesFormattingConfigurer<T>> c) {
        c.accept(new DefaultContentTypesFormattingConfigurer<>(ctpc, cts));
        return this;
    }

    @Override
    public DefaultContentTypesProcessingConfigurer<T> iterator(final Consumer<ContentTypesPlaceholderIteratorConfigurer<T>> c) {
        c.accept(new DefaultContentTypesPlaceholderIteratorConfigurer<>(ctpc, cts));
        return this;
    }

    @Override
    public <N extends Placeholder> ContentTypesProcessingConfigurer<N> forTypes(final ContentType... cts) {
        return ctc.forTypes(cts);
    }

}
