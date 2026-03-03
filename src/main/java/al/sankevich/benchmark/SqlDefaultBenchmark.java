package al.sankevich.benchmark;

import al.sankevich.benchmark.core.benchmark.SqlAbstractBenchmark;
import al.sankevich.benchmark.core.engine.DefaultEngineProvider;
import al.sankevich.benchmark.core.values.wrappable.sql.SqlDefaultValuesProvider;

import static al.sankevich.benchmark.core.Constants.DEFAULT_PROFILING;

public class SqlDefaultBenchmark extends SqlAbstractBenchmark implements SqlDefaultValuesProvider, DefaultEngineProvider {

    public SqlDefaultBenchmark() {
        super("test-default.sql");
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(DEFAULT_PROFILING);
    }
}
