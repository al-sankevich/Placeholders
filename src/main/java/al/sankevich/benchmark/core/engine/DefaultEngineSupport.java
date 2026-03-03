package al.sankevich.benchmark.core.engine;

import al.sankevich.placeholders.constants.ConfigConstants;
import al.sankevich.placeholders.engines.source.SourceProcessingEngine;
import al.sankevich.placeholders.engines.source.ext.impl.DefaultSourceProcessingEngine;

public interface DefaultEngineSupport extends EngineSupport {

    @Override
    default SourceProcessingEngine getEngine() {
        return new DefaultSourceProcessingEngine(ConfigConstants.DEFAULT_PLACEHOLDERS_PROCESSING_CONFIGURATION);
    }
}
