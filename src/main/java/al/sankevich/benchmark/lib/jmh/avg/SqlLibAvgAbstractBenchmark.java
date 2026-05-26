package al.sankevich.benchmark.lib.jmh.avg;

import static al.sankevich.placeholders.contenttypes.impl.DefaultContentTypes.SQL;

public abstract class SqlLibAvgAbstractBenchmark extends LibAvgAbstractBenchmark {

    public SqlLibAvgAbstractBenchmark(final String filename) {
        super(SQL, filename);
    }
}
