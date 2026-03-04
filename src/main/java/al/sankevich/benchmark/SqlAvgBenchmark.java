package al.sankevich.benchmark;

import al.sankevich.benchmark.core.benchmark.avg.ext.SqlAvgAbstractBenchmark;
import al.sankevich.benchmark.core.engine.ext.DefaultEngineProvider;
import al.sankevich.benchmark.core.values.wrappable.sql.SqlDefaultValuesProvider;

import static al.sankevich.benchmark.core.Constants.DEFAULT_PROFILING;

public class SqlAvgBenchmark extends SqlAvgAbstractBenchmark implements SqlDefaultValuesProvider, DefaultEngineProvider {

    public SqlAvgBenchmark() {
        super("test-default.sql");
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(DEFAULT_PROFILING);
    }
}
