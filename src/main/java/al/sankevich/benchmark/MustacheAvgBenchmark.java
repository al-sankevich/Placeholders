package al.sankevich.benchmark;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import lombok.RequiredArgsConstructor;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static al.sankevich.benchmark.core.Constants.DEFAULT_PROFILING;

@State(Scope.Benchmark)
@RequiredArgsConstructor
public class MustacheAvgBenchmark {

    private final Context context = new Context();

    private Mustache mustache;

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(DEFAULT_PROFILING);
    }

    @Setup
    public void setup() {
        mustache = new DefaultMustacheFactory() {
            @Override
            public Reader getReader(String resourceName) {
                InputStream is = getClass().getResourceAsStream("/mustache/" + resourceName);
                return new InputStreamReader(is, StandardCharsets.UTF_8);
            }
        }.compile("test-default.json");
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Warmup(iterations = 5, time = 1)
    @Measurement(iterations = 100, time = 1)
    @Fork(1)
    @Threads(1)
    public void check(Blackhole blackhole) throws IOException {
        StringWriter writer = new StringWriter();
        mustache.execute(writer, context).flush();
        blackhole.consume(writer.toString());
    }

    public static class Context {

        public Function<String, String> br() {
            return s -> new StringBuilder(s.length() + 2)
                    .append('"')
                    .append(s)
                    .append('"')
                    .toString();
        }
    }
}
