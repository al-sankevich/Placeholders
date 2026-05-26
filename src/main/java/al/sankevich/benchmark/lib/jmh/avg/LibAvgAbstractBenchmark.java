package al.sankevich.benchmark.lib.jmh.avg;

import al.sankevich.benchmark.core.jmh.AvgAbstractBenchmark;
import al.sankevich.benchmark.core.EngineProvider;
import al.sankevich.benchmark.core.utils.FileUtils;
import al.sankevich.benchmark.lib.values.ValuesProvider;
import al.sankevich.placeholders.contenttypes.ContentType;
import al.sankevich.placeholders.engines.source.SourceProcessingEngine;
import lombok.RequiredArgsConstructor;
import org.openjdk.jmh.infra.Blackhole;

@RequiredArgsConstructor
public abstract class LibAvgAbstractBenchmark extends AvgAbstractBenchmark
        implements ValuesProvider, EngineProvider<SourceProcessingEngine> {

    private final ContentType type;
    private final String filename;

    private String content;
    private SourceProcessingEngine processingEngine;

    @Override
    public void setup() {
        content = FileUtils.loadFile("lib/" + filename);
        processingEngine = getEngine();
    }

    @Override
    public void check(Blackhole blackhole) {
        String result = processingEngine.process(content, type, this);
        blackhole.consume(result);
    }
}
