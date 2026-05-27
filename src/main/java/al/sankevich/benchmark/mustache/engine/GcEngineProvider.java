package al.sankevich.benchmark.mustache.engine;

import al.sankevich.benchmark.core.EngineProvider;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;

import java.io.InputStreamReader;
import java.io.Reader;

public interface GcEngineProvider extends EngineProvider<MustacheFactory> {

    @Override
    default MustacheFactory getEngine() {
        return new DefaultMustacheFactory() {
            @Override
            public Reader getReader(String resourceName) {
                return new InputStreamReader(
                        Thread.currentThread().getContextClassLoader().getResourceAsStream("mustache/" + resourceName)
                );
            }
        };
    }
}
