package al.sankevich.benchmark.core.values.sql;

import al.sankevich.benchmark.core.values.WrappableDefaultValuesProvider;

public interface SqlDefaultValuesProvider extends WrappableDefaultValuesProvider {

    @Override
    default Character get() {
        return '\'';
    }
}
