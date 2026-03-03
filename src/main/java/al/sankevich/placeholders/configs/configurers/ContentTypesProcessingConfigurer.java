package al.sankevich.placeholders.configs.configurers;

import al.sankevich.placeholders.dtos.Placeholder;

import java.util.function.Consumer;

public interface ContentTypesProcessingConfigurer<T extends Placeholder> extends ContentTypeConfigurer {

    ContentTypesProcessingConfigurer<T> formatting(Consumer<ContentTypesFormattingConfigurer<T>> c);

    ContentTypesProcessingConfigurer<T> iterator(Consumer<ContentTypesPlaceholderIteratorConfigurer<T>> c);

}
