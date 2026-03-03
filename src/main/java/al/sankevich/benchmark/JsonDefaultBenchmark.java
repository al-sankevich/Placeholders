package al.sankevich.benchmark;

import al.sankevich.benchmark.core.benchmark.JsonAbstractBenchmark;
import al.sankevich.benchmark.core.engine.DefaultEngineProvider;
import al.sankevich.benchmark.core.values.json.JsonDefaultValuesProvider;

import static al.sankevich.benchmark.core.Constants.DEFAULT_PROFILING;

public class JsonDefaultBenchmark extends JsonAbstractBenchmark
        implements JsonDefaultValuesProvider, DefaultEngineProvider {

    public JsonDefaultBenchmark() {
        super("test-default.json");
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(DEFAULT_PROFILING);
    }
}
