package al.sankevich.benchmark.core.values.wrappable.json;

import al.sankevich.benchmark.core.values.wrappable.WrappableDefaultValuesProvider;

public interface JsonDefaultValuesProvider extends WrappableDefaultValuesProvider {

    @Override
    default Character get() {
        return '"';
    }
}
