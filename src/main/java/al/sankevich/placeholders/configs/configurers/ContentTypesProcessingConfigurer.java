package al.sankevich.placeholders.configs.configurers;

import al.sankevich.placeholders.configs.configurers.auto.Applicator;
import al.sankevich.placeholders.dtos.Placeholder;

public interface ContentTypesProcessingConfigurer<T extends Placeholder> extends ContentTypeConfigurer {

    ContentTypesProcessingConfigurer<T> formatting(Applicator<ContentTypesFormattingConfigurer<T>> c);

    ContentTypesProcessingConfigurer<T> iterator(Applicator<ContentTypesPlaceholderIteratorConfigurer<T>> c);

}
