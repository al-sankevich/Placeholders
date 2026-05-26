package al.sankevich.benchmark.core.values.wrappable;

import al.sankevich.benchmark.core.values.ValuesProvider;

import java.util.Map;
import java.util.function.Supplier;

public interface WrappableDefaultValuesProvider extends ValuesProvider, Supplier<Character> {

    Map<String, String> map = Map.of("placeholder", "placeholder");

    @Override
    default Object apply(String s) {
        return new StringBuilder(s.length() + 2)
                .append(get())
                .append(s)
                .append(get())
                .toString();
    }
}
