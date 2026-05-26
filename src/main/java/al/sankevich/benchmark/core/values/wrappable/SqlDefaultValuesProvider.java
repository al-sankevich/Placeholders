package al.sankevich.benchmark.core.values.wrappable;

public interface SqlDefaultValuesProvider extends WrappableDefaultValuesProvider {

    @Override
    default Character get() {
        return '\'';
    }
}
