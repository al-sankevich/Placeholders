package al.sankevich.benchmark;

import al.sankevich.benchmark.mustache.context.ext.DefaultContextProvider;
import al.sankevich.benchmark.mustache.jmh.avg.MustacheAvgAbstractBenchmark;
import al.sankevich.benchmark.mustache.engine.DefaultEngineProvider;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import static al.sankevich.benchmark.core.constants.ProfilingConstants.DEFAULT_PROFILING;

@State(Scope.Benchmark)
public class MustacheAvgBenchmark extends MustacheAvgAbstractBenchmark
        implements DefaultEngineProvider, DefaultContextProvider {

    public MustacheAvgBenchmark() {
        super("test-default.json");
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(DEFAULT_PROFILING);
    }
}
