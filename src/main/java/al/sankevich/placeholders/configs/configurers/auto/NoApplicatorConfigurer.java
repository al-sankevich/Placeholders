package al.sankevich.placeholders.configs.configurers.auto;

import java.util.ArrayList;
import java.util.List;

public abstract class NoApplicatorConfigurer implements AutoConfigurer {

    private final List<AutoConfigurer> cs = new ArrayList<>();

    public void addConfigurer(AutoConfigurer configurer) {
        cs.add(configurer);
    }

    @Override
    public void config() {
        cs.forEach(AutoConfigurer::config);
    }
}
