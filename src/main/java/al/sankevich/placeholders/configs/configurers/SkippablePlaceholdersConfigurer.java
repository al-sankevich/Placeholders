package al.sankevich.placeholders.configs.configurers;

import al.sankevich.placeholders.configs.configurers.auto.AutoConfigurer;

public interface SkippablePlaceholdersConfigurer extends AutoConfigurer {

    SkippablePlaceholdersConfigurer skip(String... pls);

}
