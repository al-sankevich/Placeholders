package al.sankevich.benchmark.lib.values;

import static al.sankevich.benchmark.core.constants.ProfilingConstants.VALUES_MAP;

public interface DefaultValuesProvider extends ValuesProvider {

    @Override
    default Object apply(String s) {
        return VALUES_MAP.get(s);
    }
}
