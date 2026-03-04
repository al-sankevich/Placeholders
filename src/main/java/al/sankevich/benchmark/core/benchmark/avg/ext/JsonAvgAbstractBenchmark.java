package al.sankevich.benchmark.core.benchmark.avg.ext;

import al.sankevich.benchmark.core.benchmark.avg.AvgAbstractBenchmark;

import static al.sankevich.placeholders.contenttypes.impl.DefaultContentTypes.JSON;

public abstract class JsonAvgAbstractBenchmark extends AvgAbstractBenchmark {

    public JsonAvgAbstractBenchmark(final String filename) {
        super(JSON, filename);
    }
}
