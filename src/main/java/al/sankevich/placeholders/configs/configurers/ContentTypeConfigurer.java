package al.sankevich.placeholders.configs.configurers;

import al.sankevich.placeholders.contenttypes.ContentType;
import al.sankevich.placeholders.dtos.Placeholder;

public interface ContentTypeConfigurer {

    <T extends Placeholder> ContentTypesProcessingConfigurer<T> forTypes(ContentType... cts);

}
