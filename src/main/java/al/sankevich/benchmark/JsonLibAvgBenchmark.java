package al.sankevich.benchmark;

import al.sankevich.benchmark.lib.jmh.avg.JsonLibAvgAbstractBenchmark;
import al.sankevich.benchmark.lib.engine.DefaultEngineProvider;
import al.sankevich.benchmark.lib.values.DefaultValuesProvider;

import static al.sankevich.benchmark.core.constants.ProfilingConstants.DEFAULT_PROFILING;

public class JsonLibAvgBenchmark extends JsonLibAvgAbstractBenchmark
        implements DefaultValuesProvider, DefaultEngineProvider {

    public JsonLibAvgBenchmark() {
        super("test-default.json");
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(DEFAULT_PROFILING);
    }
}
