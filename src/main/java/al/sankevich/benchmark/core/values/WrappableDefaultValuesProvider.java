package al.sankevich.benchmark.core.values;

import java.util.function.Supplier;

public interface WrappableDefaultValuesProvider extends ValuesProvider, Supplier<Character> {

    @Override
    default Object apply(String s) {
        return new StringBuilder(s.length() + 2)
                .append(get())
                .append(s)
                .append(get())
                .toString();
    }
}
