package al.sankevich.placeholders.configs.configurers.auto;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public abstract class ApplicatorConfigurer<T> implements AutoConfigurer {

    private final Map<String, Applicator<T>> cs = new HashMap<>();
    private final T t;

    protected void addApplicator(String key, Applicator<T> c) {
        cs.put(key, c);
    }

    @Override
    public void config() {
        cs.forEach((key, c) -> c.apply(t).config());
    }
}
