package al.sankevich.benchmark.core.benchmark.avg.ext;

import al.sankevich.benchmark.core.benchmark.avg.AvgAbstractBenchmark;

import static al.sankevich.placeholders.contenttypes.impl.DefaultContentTypes.SQL;

public abstract class SqlAvgAbstractBenchmark extends AvgAbstractBenchmark {

    public SqlAvgAbstractBenchmark(final String filename) {
        super(SQL, filename);
    }
}
