package al.sankevich.benchmark.core;

import java.util.function.Function;

public class Constants {

    public static final String[] DEFAULT_PROFILING = new String[] {};
    public static final String[] GC_PROFILING = new String[] {"-prof", "gc"};
    public static final String[] STACK_PROFILING = new String[] {"-prof", "stack"};

    public static Function<Character, Function<String, Object>> WRAPPABLE_DEFAULT_FUNC = character ->
            placeholder -> new StringBuilder(placeholder.length() + 2)
                    .append('"')
                    .append(placeholder)
                    .append('"')
                    .toString();
}
