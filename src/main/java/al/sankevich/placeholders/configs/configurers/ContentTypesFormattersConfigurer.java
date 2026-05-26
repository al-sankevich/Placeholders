package al.sankevich.placeholders.configs.configurers;

import al.sankevich.placeholders.configs.configurers.auto.AutoConfigurer;
import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.formatters.PlaceholderValueFormatter;

public interface ContentTypesFormattersConfigurer<T extends Placeholder, F extends PlaceholderValueFormatter<? super T>>
        extends AutoConfigurer {

    ContentTypesFormattersConfigurer<T, F> addFormatter(F pvf);

}
