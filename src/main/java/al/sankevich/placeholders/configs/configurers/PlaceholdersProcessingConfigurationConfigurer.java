package al.sankevich.placeholders.configs.configurers;

import al.sankevich.placeholders.configs.configurations.PlaceholdersProcessingConfiguration;
import al.sankevich.placeholders.configs.configurers.auto.Applicator;

import java.util.function.Supplier;

public interface PlaceholdersProcessingConfigurationConfigurer<T extends PlaceholdersProcessingConfiguration>
        extends Supplier<T> {

    PlaceholdersProcessingConfigurationConfigurer<T> contentTypes(Applicator<ContentTypeConfigurer> c);

    PlaceholdersProcessingConfigurationConfigurer<T> placeholders(Applicator<SkippablePlaceholdersConfigurer> c);
}
