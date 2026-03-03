package al.sankevich.benchmark.core.values.json;

import al.sankevich.benchmark.core.values.WrappableDefaultValuesProvider;

public interface JsonDefaultValuesProvider extends WrappableDefaultValuesProvider {

    @Override
    default Character get() {
        return '"';
    }
}
