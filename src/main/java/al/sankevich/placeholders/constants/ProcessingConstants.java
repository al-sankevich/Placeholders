package al.sankevich.placeholders.constants;

import tools.jackson.databind.ObjectMapper;

/**
 * Contains constants for {@code processing} needs.
 */
public class ProcessingConstants {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static final String JSON_UNIT_ANY_STRING = "json-unit.any-string";
    public static final String JSON_UNIT_ANY_NUMBER = "json-unit.any-number";
    public static final String NULL = "null";
}
