package al.sankevich.benchmark.core.values.wrappable.sql;

import al.sankevich.benchmark.core.values.wrappable.WrappableDefaultValuesProvider;

public interface SqlDefaultValuesProvider extends WrappableDefaultValuesProvider {

    @Override
    default Character get() {
        return '\'';
    }
}
