package al.sankevich.benchmark.core.engine;

import al.sankevich.placeholders.engines.source.SourceProcessingEngine;

public interface EngineProvider {

    SourceProcessingEngine getEngine();
}
