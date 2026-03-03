package al.sankevich.benchmark.core.func;

import al.sankevich.benchmark.core.Constants;

import java.util.function.Function;
import java.util.function.Supplier;

public interface WrappableDefaultFuncSupport extends FuncSupport, Supplier<Character> {

    @Override
    default Function<String, Object> getFunc() {
        return Constants.WRAPPABLE_DEFAULT_FUNC.apply(get());
    }
}
