package al.sankevich.benchmark;

import al.sankevich.benchmark.mustache.context.ext.DefaultContextProvider;
import al.sankevich.benchmark.mustache.engine.GcEngineProvider;
import al.sankevich.benchmark.mustache.jmh.avg.MustacheAvgAbstractBenchmark;

import static al.sankevich.benchmark.core.constants.ProfilingConstants.GC_PROFILING;

public class GcJsonMustacheAvgBenchmark extends MustacheAvgAbstractBenchmark
        implements GcEngineProvider, DefaultContextProvider {

    public GcJsonMustacheAvgBenchmark() {
        super("test-default.json");
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(GC_PROFILING);
    }
}
