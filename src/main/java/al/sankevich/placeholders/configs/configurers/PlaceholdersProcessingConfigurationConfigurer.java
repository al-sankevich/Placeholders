package al.sankevich.placeholders.configs.configurers;

import al.sankevich.placeholders.configs.configurations.PlaceholdersProcessingConfiguration;

import java.util.function.Consumer;

public interface PlaceholdersProcessingConfigurationConfigurer<T extends PlaceholdersProcessingConfiguration> {

    PlaceholdersProcessingConfigurationConfigurer<T> contentTypes(Consumer<ContentTypeConfigurer> c);

    PlaceholdersProcessingConfigurationConfigurer<T> placeholders(Consumer<SkippablePlaceholdersConfigurer> c);

    T get();

}
