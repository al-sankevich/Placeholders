package al.sankevich.benchmark;

import al.sankevich.benchmark.lib.jmh.avg.SqlLibAvgAbstractBenchmark;
import al.sankevich.benchmark.lib.engine.DefaultEngineProvider;
import al.sankevich.benchmark.lib.values.DefaultValuesProvider;

import static al.sankevich.benchmark.core.constants.ProfilingConstants.DEFAULT_PROFILING;

public class DefaultSqlLibAvgBenchmark extends SqlLibAvgAbstractBenchmark
        implements DefaultValuesProvider, DefaultEngineProvider {

    public DefaultSqlLibAvgBenchmark() {
        super("test-default.sql");
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(DEFAULT_PROFILING);
    }
}
