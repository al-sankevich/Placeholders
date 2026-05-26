package al.sankevich.benchmark.lib.engine;

import al.sankevich.benchmark.core.EngineProvider;
import al.sankevich.placeholders.constants.ConfigConstants;
import al.sankevich.placeholders.engines.source.SourceProcessingEngine;
import al.sankevich.placeholders.engines.source.ext.impl.DefaultSourceProcessingEngine;

public interface DefaultEngineProvider extends EngineProvider<SourceProcessingEngine> {

    @Override
    default SourceProcessingEngine getEngine() {
        return new DefaultSourceProcessingEngine(ConfigConstants.DEFAULT_PLACEHOLDERS_PROCESSING_CONFIGURATION);
    }
}
