package al.sankevich.benchmark;

import al.sankevich.placeholders.constants.ConfigConstants;
import al.sankevich.placeholders.engines.source.SourceProcessingEngine;
import al.sankevich.placeholders.engines.source.ext.impl.DefaultSourceProcessingEngine;
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
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static al.sankevich.placeholders.contenttypes.impl.DefaultContentTypes.JSON;

@State(Scope.Benchmark)
public class DefaultBenchmark {

    private String content;
    private SourceProcessingEngine PLACEHOLDER_PROCESSOR;
    private Function<String, Object> func;

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(new String[] {
                //"-prof", "gc"
                //"-prof", "stack"
                //"-prof", "comp"
        });
    }

    @Setup
    public void setup() throws IOException {
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("test-default.json")) {
            content = new String(is.readAllBytes());
        }

        StringBuilder sb = new StringBuilder();
        func = placeholder -> {
            String str = sb.append('"').append(placeholder).append('"').toString();
            sb.setLength(0);
            return str;
        };

        PLACEHOLDER_PROCESSOR = new DefaultSourceProcessingEngine(
                ConfigConstants.DEFAULT_PLACEHOLDERS_PROCESSING_CONFIGURATION
        );
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
        String result = PLACEHOLDER_PROCESSOR.process(content, JSON, func);
        blackhole.consume(result);
    }
}
