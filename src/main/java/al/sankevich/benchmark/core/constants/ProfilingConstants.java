package al.sankevich.benchmark.core.constants;

import java.util.Map;

public class ProfilingConstants {

    public static final String[] DEFAULT_PROFILING = new String[] {};
    public static final String[] GC_PROFILING = new String[] {"-prof", "gc"};
    public static final String[] STACK_PROFILING = new String[] {"-prof", "stack"};

    public static final Map<String, String> VALUES_MAP = Map.of("placeholder", "placeholder");
}
