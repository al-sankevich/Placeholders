package al.sankevich.benchmark.core.func.sql;

import al.sankevich.benchmark.core.func.WrappableDefaultFuncSupport;

public interface SqlDefaultFuncSupport extends WrappableDefaultFuncSupport {

    @Override
    default Character get() {
        return '\'';
    }
}
