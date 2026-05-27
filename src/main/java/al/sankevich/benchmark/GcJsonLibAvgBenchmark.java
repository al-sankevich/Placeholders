package al.sankevich.benchmark;

import al.sankevich.benchmark.lib.engine.DefaultEngineProvider;
import al.sankevich.benchmark.lib.jmh.avg.JsonLibAvgAbstractBenchmark;
import al.sankevich.benchmark.lib.values.DefaultValuesProvider;

import static al.sankevich.benchmark.core.constants.ProfilingConstants.GC_PROFILING;

public class GcJsonLibAvgBenchmark  extends JsonLibAvgAbstractBenchmark
        implements DefaultValuesProvider, DefaultEngineProvider {

    public GcJsonLibAvgBenchmark() {
        super("test-default.json");
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(GC_PROFILING);
    }
}
