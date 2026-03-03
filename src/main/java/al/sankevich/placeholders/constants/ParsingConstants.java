package al.sankevich.placeholders.constants;

import al.sankevich.placeholders.dtos.Component;

/**
 * Contains constants for {@code parsing} needs.
 */
public class ParsingConstants {
    public static final char ESCAPING_SYMBOL = '^';
    public static final char FORMATS_SEPARATING_SYMBOL = ',';
    public static final char SECTIONS_SEPARATING_SYMBOL = ':';
    public static final char FORMAT_SEPARATING_SYMBOL = '=';

    public static final char PLACEHOLDER_LEFT_BORDER_SYMBOL = '{';
    public static final char PLACEHOLDER_RIGHT_BORDER_SYMBOL = '}';
    public static final char PLACEHOLDER_STARTING_SYMBOL = '$';

    public static final String PLACEHOLDER_NAME = "Placeholder name";
    public static final String ENABLED_FORMAT_NAME = "Enabled format name";
    public static final String ENABLED_FORMAT_VALUE = "Enabled format value";
    public static final String DISABLED_FORMAT_NAME = "Disabled format name";

    public static final Component PLACEHOLDER_NAME_COMPONENT = new Component(
            PLACEHOLDER_NAME,
            SECTIONS_SEPARATING_SYMBOL
    );
    public static final Component ENABLED_FORMAT_NAME_COMPONENT = new Component(
            ENABLED_FORMAT_NAME,
            FORMAT_SEPARATING_SYMBOL,
            FORMATS_SEPARATING_SYMBOL,
            SECTIONS_SEPARATING_SYMBOL
    );
    public static final Component ENABLED_FORMAT_VALUE_COMPONENT = new Component(
            ENABLED_FORMAT_VALUE,
            FORMATS_SEPARATING_SYMBOL,
            SECTIONS_SEPARATING_SYMBOL
    );
    public static final Component DISABLED_FORMAT_NAME_COMPONENT = new Component(
            DISABLED_FORMAT_NAME,
            FORMATS_SEPARATING_SYMBOL,
            SECTIONS_SEPARATING_SYMBOL
    );
}
