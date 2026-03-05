package al.sankevich.utils;

public class SourceUtils {

    // correct
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

    public static final String SOURCE_WITH_ESCAPED_RESERVED_CHARS = """
            {
                "key": "${placeholder^^^}^::f1^^^}^=^,^:=v1^^^}^,^::v1^^^}^,^:}"
            }
            """;

    // incorrect
    public static final String SOURCE_WITHOUT_PLACEHOLDER_NAME = """
            {
                "key": "${}"
            }
            """;

    public static final String SOURCE_WITHOUT_ENABLED_FORMAT_NAME_BEFORE_VALUE = """
            {
                "key": "${placeholder:=v1}"
            }
            """;

    public static final String SOURCE_WITHOUT_ENABLED_FORMAT_VALUE_AFTER_NAME = """
            {
                "key": "${placeholder:f1=}"
            }
            """;

    public static final String SOURCE_WITHOUT_ENABLED_FORMAT_NAME_BEFORE_COMMA = """
            {
                "key": "${placeholder:,f2}"
            }
            """;

    public static final String SOURCE_WITHOUT_ENABLED_FORMAT_NAME_AFTER_COMMA = """
            {
                "key": "${placeholder:f1,}"
            }
            """;

    public static final String SOURCE_WITHOUT_DISABLED_FORMAT_NAME_BEFORE_COMMA = """
            {
                "key": "${placeholder::,f2}"
            }
            """;

    public static final String SOURCE_WITHOUT_DISABLED_FORMAT_NAME_AFTER_COMMA = """
            {
                "key": "${placeholder::f1,}"
            }
            """;

    public static final String SOURCE_WITH_ESCAPED_UNRESERVED_CHAR_IN_PLACEHOLDER_NAME = """
            {
                "key": "${p^laceholder}"
            }
            """;

    public static final String SOURCE_WITH_ESCAPED_UNRESERVED_CHAR_IN_ENABLED_FORMAT_NAME = """
            {
                "key": "${placeholder:f^1}"
            }
            """;

    public static final String SOURCE_WITH_ESCAPED_UNRESERVED_CHAR_IN_ENABLED_FORMAT_VALUE = """
            {
                "key": "${placeholder:f1=v^1}"
            }
            """;

    public static final String SOURCE_WITH_ESCAPED_UNRESERVED_CHAR_IN_DISABLED_FORMAT_NAME = """
            {
                "key": "${placeholder::f^1}"
            }
            """;
}
