package al.sankevich.benchmark;

import al.sankevich.benchmark.core.benchmark.avg.ext.JsonAvgAbstractBenchmark;
import al.sankevich.benchmark.core.engine.ext.DefaultEngineProvider;
import al.sankevich.benchmark.core.values.wrappable.json.JsonDefaultValuesProvider;

import static al.sankevich.benchmark.core.Constants.DEFAULT_PROFILING;

public class JsonAvgBenchmark extends JsonAvgAbstractBenchmark
        implements JsonDefaultValuesProvider, DefaultEngineProvider {

    public JsonAvgBenchmark() {
        super("test-default.json");
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(DEFAULT_PROFILING);
    }
}
