package al.sankevich.placeholders.configs.configurers.impl;

import al.sankevich.placeholders.configs.configurations.PlaceholdersProcessingConfiguration;
import al.sankevich.placeholders.configs.configurers.ContentTypeConfigurer;
import al.sankevich.placeholders.configs.configurers.PlaceholdersProcessingConfigurationConfigurer;
import al.sankevich.placeholders.configs.configurers.SkippablePlaceholdersConfigurer;
import al.sankevich.placeholders.configs.configurers.auto.Applicator;
import al.sankevich.placeholders.configs.configurers.auto.ApplicatorConfigurer;

public class DefaultPlaceholdersProcessingConfigurationConfigurer
        extends ApplicatorConfigurer<PlaceholdersProcessingConfiguration>
        implements PlaceholdersProcessingConfigurationConfigurer<PlaceholdersProcessingConfiguration> {

    private final PlaceholdersProcessingConfiguration ppc;

    private DefaultPlaceholdersProcessingConfigurationConfigurer(final PlaceholdersProcessingConfiguration ppc) {
        super(ppc);
        this.ppc = ppc;
    }

    public static DefaultPlaceholdersProcessingConfigurationConfigurer init() {
        return from(new PlaceholdersProcessingConfiguration());
    }

    public static DefaultPlaceholdersProcessingConfigurationConfigurer from(
            final PlaceholdersProcessingConfiguration ppc
    ) {
        return new DefaultPlaceholdersProcessingConfigurationConfigurer(ppc);
    }

    @Override
    public DefaultPlaceholdersProcessingConfigurationConfigurer contentTypes(
            final Applicator<ContentTypeConfigurer> c
    ) {
        addApplicator(
                "contentType", (ppc) -> {
                    ContentTypeConfigurer ctc = new DefaultContentTypeConfigurer(ppc.getCtpc());
                    c.apply(ctc);
                    return ctc;
                }
        );
        return this;
    }

    @Override
    public DefaultPlaceholdersProcessingConfigurationConfigurer placeholders(
            final Applicator<SkippablePlaceholdersConfigurer> c
    ) {
        addApplicator(
                "skippablePlaceholders", (ppc) -> c.apply(
                        new DefaultSkippablePlaceholdersConfigurer(ppc.getSpc())
                )
        );
        return this;
    }

    @Override
    public PlaceholdersProcessingConfiguration get() {
        config();
        return ppc;
    }

}
