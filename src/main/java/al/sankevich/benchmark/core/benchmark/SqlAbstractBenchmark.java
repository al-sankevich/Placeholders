package al.sankevich.benchmark.core.benchmark;

import static al.sankevich.placeholders.contenttypes.impl.DefaultContentTypes.SQL;

public abstract class SqlAbstractBenchmark extends AbstractBenchmark {

    public SqlAbstractBenchmark(final String filename) {
        super(SQL, filename);
    }
}
