package al.sankevich.utils;

public class ExceptionUtils {

    public static final String EMPTY_PLACEHOLDER_NAME_MESSAGE = """
            Placeholder name is empty (placeholder position in source is 14)
            {
                "key": "${<<ERROR>>}"
            }
            """;

    public static final String EMPTY_ENABLED_FORMAT_NAME_MESSAGE = """
            Enabled format name is empty (placeholder position in source is 14)
            {
                "key": "${placeholder:<<ERROR>>=v1}"
            }
            """;
    public static final String EMPTY_ENABLED_FORMAT_VALUE_MESSAGE = """
            Enabled format value is empty (placeholder position in source is 14)
            {
                "key": "${placeholder:f1=<<ERROR>>}"
            }
            """;

    public static final String EMPTY_ENABLED_FORMAT_NAME_BEFORE_COMMA_MESSAGE = """
            Enabled format name is empty (placeholder position in source is 14)
            {
                "key": "${placeholder:<<ERROR>>,f2}"
            }
            """;

    public static final String EMPTY_ENABLED_FORMAT_NAME_AFTER_COMMA_MESSAGE = """
            Enabled format name is empty (placeholder position in source is 14)
            {
                "key": "${placeholder:f1,<<ERROR>>}"
            }
            """;

    public static final String EMPTY_DISABLED_FORMAT_NAME_BEFORE_COMMA_MESSAGE = """
            Disabled format name is empty (placeholder position in source is 14)
            {
                "key": "${placeholder::<<ERROR>>,f2}"
            }
            """;

    public static final String EMPTY_DISABLED_FORMAT_NAME_AFTER_COMMA = """
            Disabled format name is empty (placeholder position in source is 14)
            {
                "key": "${placeholder::f1,<<ERROR>>}"
            }
            """;
}
