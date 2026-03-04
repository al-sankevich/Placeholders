package al.sankevich.utils;

public class SourceUtils {

    public static final String SIMPLE_SOURCE = """
            {
                "key": "${placeholder}"
            }
            """;
    public static final String SOURCE_WITH_ENABLED_FORMAT = """
            {
                "key": "${placeholder:f1}"
            }
            """;
    public static final String SOURCE_WITH_ENABLED_FORMAT_WITH_VALUE = """
            {
                "key": "${placeholder:f1=v1}"
            }
            """;
    public static final String SOURCE_WITH_ENABLED_FORMATS = """
            {
                "key": "${placeholder:f1,f2}"
            }
            """;
    public static final String SOURCE_WITH_ENABLED_FORMATS_WITH_VALUES = """
            {
                "key": "${placeholder:f1=v1,f2=v2}"
            }
            """;

    public static final String SOURCE_WITH_DISABLED_FORMAT = """
            {
                "key": "${placeholder::f1}"
            }
            """;
    public static final String SOURCE_WITH_DISABLED_FORMATS = """
            {
                "key": "${placeholder::f1,f2}"
            }
            """;
}
