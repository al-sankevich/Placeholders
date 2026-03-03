package al.sankevich.placeholders.configs.configurations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SkippablePlaceholdersConfiguration {

    private final Set<String> defaultSkippablePlaceholders = new HashSet<>();

    public void add(final String... pls) {
        defaultSkippablePlaceholders.addAll(Arrays.asList(pls));
    }

    public Set<String> get() {
        return defaultSkippablePlaceholders;
    }

}
