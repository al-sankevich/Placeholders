package al.sankevich.benchmark.core.benchmark;

import al.sankevich.benchmark.core.engine.EngineProvider;
import al.sankevich.benchmark.core.values.ValuesProvider;
import al.sankevich.placeholders.contenttypes.ContentType;
import al.sankevich.placeholders.engines.source.SourceProcessingEngine;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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

import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@RequiredArgsConstructor
public abstract class AbstractBenchmark implements ValuesProvider, EngineProvider {

    private final ContentType type;
    private final String file;

    private String content;
    private SourceProcessingEngine processingEngine;

    @Setup
    @SneakyThrows
    public void setup() {
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(file)) {
            content = new String(Objects.requireNonNull(is).readAllBytes());
        }

        processingEngine = getEngine();
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Warmup(iterations = 5, time = 1)
    @Measurement(iterations = 10, time = 1)
    @Fork(5)
    @Threads(1)
    public void check(Blackhole blackhole) {
        String result = processingEngine.process(content, type, this);
        blackhole.consume(result);
    }
}
