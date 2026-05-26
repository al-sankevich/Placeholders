package al.sankevich.benchmark.mustache.jmh.avg;

import al.sankevich.benchmark.core.jmh.AvgAbstractBenchmark;
import al.sankevich.benchmark.core.EngineProvider;
import al.sankevich.benchmark.mustache.context.ContextProvider;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.openjdk.jmh.infra.Blackhole;

import java.io.StringWriter;

@RequiredArgsConstructor
public abstract class MustacheAvgAbstractBenchmark extends AvgAbstractBenchmark
        implements EngineProvider<MustacheFactory>, ContextProvider {

    private final String filename;

    private MustacheFactory mustacheFactory;

    @Override
    public void setup() {
        mustacheFactory = getEngine();
    }

    @Override
    @SneakyThrows
    public void check(Blackhole blackhole) {
        Mustache mustache = mustacheFactory.compile(filename);
        StringWriter writer = new StringWriter();
        mustache.execute(writer, getContext()).flush();
        blackhole.consume(writer.toString());
    }
}
