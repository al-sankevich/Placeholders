package al.sankevich.benchmark;

import al.sankevich.benchmark.core.benchmark.SqlAbstractBenchmark;
import al.sankevich.benchmark.core.engine.DefaultEngineSupport;
import al.sankevich.benchmark.core.func.sql.SqlDefaultFuncSupport;

import static al.sankevich.benchmark.core.Constants.DEFAULT_PROFILING;

public class SqlDefaultBenchmark extends SqlAbstractBenchmark
        implements SqlDefaultFuncSupport, DefaultEngineSupport {

    public SqlDefaultBenchmark() {
        super("test-default.sql");
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(DEFAULT_PROFILING);
    }
}
