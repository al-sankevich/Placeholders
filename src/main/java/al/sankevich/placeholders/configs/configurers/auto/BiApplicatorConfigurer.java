package al.sankevich.placeholders.configs.configurers.auto;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

@RequiredArgsConstructor
public abstract class BiApplicatorConfigurer<T, U> implements AutoConfigurer {

    private final Map<String, BiApplicator<T, U>> cs = new HashMap<>();
    private final T t;
    private final U u;

    public void addApplicator(String key, BiApplicator<T, U> ac) {
        cs.put(key, ac);
    }

    public void addConsumer(String key, BiConsumer<T, U> ac) {
        addApplicator(key, (t, u) -> {
            ac.accept(t, u);
            return null;
        });
    }

    @Override
    public void config() {
        cs.forEach((key, c) -> {
            AutoConfigurer ac = c.accept(t, u);
            if (ac != null) {
                ac.config();
            }
        });
    }
}
