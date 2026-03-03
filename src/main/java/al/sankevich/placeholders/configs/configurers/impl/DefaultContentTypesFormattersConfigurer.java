package al.sankevich.placeholders.configs.configurers.impl;

import al.sankevich.placeholders.configs.configurations.ContentTypesProcessingConfiguration;
import al.sankevich.placeholders.configs.configurers.ContentTypesFormattersConfigurer;
import al.sankevich.placeholders.contenttypes.ContentType;
import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.formatters.PlaceholderValueFormatter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultContentTypesFormattersConfigurer<T extends Placeholder, F extends PlaceholderValueFormatter<? super T>> implements ContentTypesFormattersConfigurer<T, F> {

    private final ContentTypesProcessingConfiguration ctpc;
    private final ContentType[] cts;

    @Override
    public DefaultContentTypesFormattersConfigurer<T, F> addFormatter(final F pvf) {
        for (ContentType ct : cts) {
            ctpc.addFormatter(ct, pvf);
        }
        return this;
    }
}
