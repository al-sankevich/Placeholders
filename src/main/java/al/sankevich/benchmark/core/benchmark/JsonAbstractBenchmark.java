package al.sankevich.benchmark.core.benchmark;

import static al.sankevich.placeholders.contenttypes.impl.DefaultContentTypes.JSON;

public abstract class JsonAbstractBenchmark extends AbstractBenchmark {

    public JsonAbstractBenchmark(final String filename) {
        super(JSON, filename);
    }
}
