package al.sankevich.placeholders.configs.configurers.impl;

import al.sankevich.placeholders.configs.configurations.PlaceholdersProcessingConfiguration;
import al.sankevich.placeholders.configs.configurers.ContentTypeConfigurer;
import al.sankevich.placeholders.configs.configurers.PlaceholdersProcessingConfigurationConfigurer;
import al.sankevich.placeholders.configs.configurers.SkippablePlaceholdersConfigurer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@RequiredArgsConstructor(staticName = "from", access = AccessLevel.PRIVATE)
public class DefaultPlaceholdersProcessingConfigurationConfigurer
        implements PlaceholdersProcessingConfigurationConfigurer<PlaceholdersProcessingConfiguration> {

    private final PlaceholdersProcessingConfiguration ppc;
    private final List<Consumer<PlaceholdersProcessingConfiguration>> cs = new ArrayList<>();

    public static DefaultPlaceholdersProcessingConfigurationConfigurer init() {
        return from(new PlaceholdersProcessingConfiguration());
    }

    @Override
    public DefaultPlaceholdersProcessingConfigurationConfigurer contentTypes(
            final Consumer<ContentTypeConfigurer> c
    ) {
        cs.add(
                (ppc) -> c.accept(
                        new DefaultContentTypeConfigurer(ppc.getCtpc())
                )
        );
        return this;
    }

    @Override
    public DefaultPlaceholdersProcessingConfigurationConfigurer placeholders(
            final Consumer<SkippablePlaceholdersConfigurer> c
    ) {
        cs.add(
                (ppc) -> c.accept(
                        new DefaultSkippablePlaceholdersConfigurer(ppc.getSpc())
                )
        );
        return this;
    }

    @Override
    public PlaceholdersProcessingConfiguration get() {
        cs.forEach(c -> c.accept(this.ppc));
        return ppc;
    }

}
