package al.sankevich.benchmark.lib.jmh.avg;

import static al.sankevich.placeholders.contenttypes.impl.DefaultContentTypes.JSON;

public abstract class JsonLibAvgAbstractBenchmark extends LibAvgAbstractBenchmark {

    public JsonLibAvgAbstractBenchmark(final String filename) {
        super(JSON, filename);
    }
}
