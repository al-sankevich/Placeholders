package al.sankevich.placeholders.configs.configurers.impl;

import al.sankevich.placeholders.configs.configurations.ContentTypesProcessingConfiguration;
import al.sankevich.placeholders.configs.configurers.ContentTypeConfigurer;
import al.sankevich.placeholders.contenttypes.ContentType;
import al.sankevich.placeholders.dtos.Placeholder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultContentTypeConfigurer implements ContentTypeConfigurer {

    private final ContentTypesProcessingConfiguration ctpc;

    @Override
    public <T extends Placeholder> DefaultContentTypesProcessingConfigurer<T> forTypes(final ContentType... cts) {
        return new DefaultContentTypesProcessingConfigurer<>(this, ctpc, cts);
    }

}
