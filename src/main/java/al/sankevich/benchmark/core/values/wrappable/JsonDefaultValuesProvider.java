package al.sankevich.benchmark.core.values.wrappable;

public interface JsonDefaultValuesProvider extends WrappableDefaultValuesProvider {

    @Override
    default Character get() {
        return '"';
    }
}
