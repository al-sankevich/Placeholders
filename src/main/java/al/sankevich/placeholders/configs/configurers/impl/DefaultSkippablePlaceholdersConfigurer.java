package al.sankevich.placeholders.configs.configurers.impl;

import al.sankevich.placeholders.configs.configurations.SkippablePlaceholdersConfiguration;
import al.sankevich.placeholders.configs.configurers.SkippablePlaceholdersConfigurer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultSkippablePlaceholdersConfigurer implements SkippablePlaceholdersConfigurer {

    private final SkippablePlaceholdersConfiguration spc;

    @Override
    public DefaultSkippablePlaceholdersConfigurer skip(final String... pls) {
        spc.add(pls);
        return this;
    }

}
