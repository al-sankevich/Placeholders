package al.sankevich.benchmark.mustache.engine;

import al.sankevich.benchmark.core.EngineProvider;
import al.sankevich.benchmark.core.utils.FileUtils;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;

import java.io.Reader;
import java.io.StringReader;

public interface DefaultEngineProvider extends EngineProvider<MustacheFactory> {

    @Override
    default MustacheFactory getEngine() {
        return new DefaultMustacheFactory() {
            @Override
            public Reader getReader(String resourceName) {
                return new StringReader(FileUtils.loadFile("mustache/" + resourceName));
            }
        };
    }
}
