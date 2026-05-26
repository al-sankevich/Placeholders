package al.sankevich.benchmark.mustache.context.ext;

import al.sankevich.benchmark.mustache.context.ContextProvider;

import java.util.Map;

public interface DefaultContextProvider extends ContextProvider {

    Map<String, String> map = Map.of("placeholder", "placeholder");

    @Override
    default Object[] getContext() {
        return new Object[] {map};
    }
}
