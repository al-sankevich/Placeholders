package al.sankevich.benchmark;

import al.sankevich.benchmark.mustache.context.ext.DefaultContextProvider;
import al.sankevich.benchmark.mustache.engine.DefaultEngineProvider;
import al.sankevich.benchmark.mustache.jmh.avg.MustacheAvgAbstractBenchmark;

import static al.sankevich.benchmark.core.constants.ProfilingConstants.DEFAULT_PROFILING;

public class DefaultJsonMustacheAvgBenchmark extends MustacheAvgAbstractBenchmark
        implements DefaultEngineProvider, DefaultContextProvider {

    public DefaultJsonMustacheAvgBenchmark() {
        super("test-default.json");
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(DEFAULT_PROFILING);
    }
}
