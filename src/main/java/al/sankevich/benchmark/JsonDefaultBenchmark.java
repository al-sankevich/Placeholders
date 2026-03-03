package al.sankevich.benchmark;

import al.sankevich.benchmark.core.benchmark.JsonAbstractBenchmark;
import al.sankevich.benchmark.core.engine.DefaultEngineSupport;
import al.sankevich.benchmark.core.func.json.JsonDefaultFuncSupport;

import static al.sankevich.benchmark.core.Constants.DEFAULT_PROFILING;

public class JsonDefaultBenchmark extends JsonAbstractBenchmark
        implements JsonDefaultFuncSupport, DefaultEngineSupport {

    public JsonDefaultBenchmark() {
        super("test-default.json");
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(DEFAULT_PROFILING);
    }
}
