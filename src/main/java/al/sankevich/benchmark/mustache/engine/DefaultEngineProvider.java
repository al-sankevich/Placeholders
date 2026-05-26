package al.sankevich.benchmark.mustache.engine;

import al.sankevich.benchmark.core.EngineProvider;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public interface DefaultEngineProvider extends EngineProvider<MustacheFactory> {

    @Override
    default MustacheFactory getEngine() {
        return new DefaultMustacheFactory() {
            @Override
            public Reader getReader(String resourceName) {
                InputStream is = getClass().getResourceAsStream("/mustache/" + resourceName);
                return new InputStreamReader(is, StandardCharsets.UTF_8);
            }
        };
    }
}
