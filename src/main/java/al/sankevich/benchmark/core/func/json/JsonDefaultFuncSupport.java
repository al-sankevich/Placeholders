package al.sankevich.benchmark.core.func.json;

import al.sankevich.benchmark.core.func.WrappableDefaultFuncSupport;

public interface JsonDefaultFuncSupport extends WrappableDefaultFuncSupport {

    @Override
    default Character get() {
        return '"';
    }
}
