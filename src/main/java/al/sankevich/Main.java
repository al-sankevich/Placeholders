package al.sankevich;

import al.sankevich.placeholders.constants.ConfigConstants;
import al.sankevich.placeholders.contenttypes.ContentType;
import al.sankevich.placeholders.engines.source.SourceProcessingEngine;
import al.sankevich.placeholders.engines.source.ext.impl.DefaultSourceProcessingEngine;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static al.sankevich.placeholders.contenttypes.impl.DefaultContentTypes.JSON;
import static al.sankevich.placeholders.contenttypes.impl.DefaultContentTypes.SQL;

@Slf4j
public class Main {

    private static final SourceProcessingEngine PLACEHOLDER_PROCESSOR = new DefaultSourceProcessingEngine(
            ConfigConstants.DEFAULT_PLACEHOLDERS_PROCESSING_CONFIGURATION
    );
    private static final ClassLoader classloader = Thread.currentThread().getContextClassLoader();

    private static final StringBuilder sb = new StringBuilder();
    private static final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private static final Function<String, Object> func = placeholder -> {
        if (placeholder.equals("null_placeholder")) {
            return null;
        }

        if (placeholder.equals("complex_placeholder")) {
            return new ArrayList<>(List.of("a", "b", "c"));
        }

        String str = sb.append('!').append(placeholder).append('!').toString();
        sb.setLength(0);
        return str;

    };

    private static void process(final String fileName, final ContentType type) throws IOException {
        log.info("Processing file {}", fileName);
        try (InputStream is = classloader.getResourceAsStream(fileName)) {
            String content = new String(Objects.requireNonNull(is).readAllBytes());
            String result = PLACEHOLDER_PROCESSOR.process(content, type, func);
            log.info("{}", content);
            log.info("{}", result);
        }
    }

    @SneakyThrows
    private static void process(final List<Object> list) {
        log.info("Processing list");
        log.info("{}", objectMapper.writeValueAsString(list));
        List<Object> result = PLACEHOLDER_PROCESSOR.process(list, func);
        log.info("{}", objectMapper.writeValueAsString(result));
    }

    @SneakyThrows
    private static void process(final Map<String, Object> map) {
        log.info("Processing map");
        log.info("{}", objectMapper.writeValueAsString(map));
        Map<String, Object> result = PLACEHOLDER_PROCESSOR.process(map, func);
        log.info("{}", objectMapper.writeValueAsString(result));
    }

    static class DefaultJson {
        public static void main(String[] args) throws IOException {
            process("test-default.json", JSON);
        }
    }

    static class CachedJson {
        public static void main(String[] args) throws IOException {
            process("test-default.json", JSON);
            process("test-default.json", JSON);
        }
    }

    static class NullJson {
        public static void main(String[] args) throws IOException {
            process("test-null.json", JSON);
        }
    }

    static class FormattedJson {
        public static void main(String[] args) throws IOException {
            process("test-formatted.json", JSON);
        }
    }

    static class StringPartJson {
        public static void main(String[] args) throws IOException {
            process("test-string-part.json", JSON);
        }
    }

    static class ArrayJson {
        public static void main(String[] args) throws IOException {
            process("test-array.json", JSON);
        }
    }

    static class FormattedArrayJson {
        public static void main(String[] args) throws IOException {
            process("test-array-formatted.json", JSON);
        }
    }

    static class DefaultSql {
        public static void main(String[] args) throws IOException {
            process("test-default.sql", SQL);
        }
    }

    static class CachedSql {
        public static void main(String[] args) throws IOException {
            process("test-default.sql", SQL);
            process("test-default.sql", SQL);
        }
    }

    static class NullSql {
        public static void main(String[] args) throws IOException {
            process("test-null.sql", SQL);
        }
    }

    static class FormattedSql {
        public static void main(String[] args) throws IOException {
            process("test-formatted.sql", SQL);
        }
    }

    static class StringPartSql {
        public static void main(String[] args) throws IOException {
            process("test-string-part.sql", SQL);
        }
    }

    static class SimpleList {
        public static void main(String[] args) {
            List<Object> list = new ArrayList<>(List.of("not_placeholder", "${placeholder}", 5));
            process(list);
        }
    }

    static class ListWithNull {
        public static void main(String[] args) {
            List<Object> list = new ArrayList<>(List.of("not_placeholder", "${null_placeholder}", 5));
            process(list);
        }
    }

    static class InnerList {
        public static void main(String[] args) {
            List<Object> innerList = new ArrayList<>(List.of("not_placeholder", "${placeholder}", 5));
            List<Object> list = new ArrayList<>(List.of("${another_placeholder}", innerList, true));
            process(list);
        }
    }

    static class ListWithComplexPlaceholder {
        public static void main(String[] args) {
            List<Object> list = new ArrayList<>(List.of("not_placeholder", "${complex_placeholder}", 5));
            process(list);
        }
    }

    static class ListWithFormattedComplexPlaceholder {
        public static void main(String[] args) {
            List<Object> list = new ArrayList<>(List.of("not_placeholder", "${complex_placeholder::nw}", 5));
            process(list);
        }
    }

    static class SimpleMap {
        public static void main(String[] args) {
            Map<String, Object> map = new HashMap<>(Map.of(
                    "key1", "not_placeholder",
                    "key2", "${placeholder}",
                    "key3", 5
            ));
            process(map);
        }
    }

    static class MapWithNull {
        public static void main(String[] args) {
            Map<String, Object> map = new HashMap<>(Map.of(
                    "key1", "not_placeholder",
                    "key2", "${placeholder_null}",
                    "key3", 5
            ));
            process(map);
        }
    }

    static class InnerMap {
        public static void main(String[] args) {
            Map<String, Object> innerMap = new HashMap<>(Map.of(
                    "key1", "not_placeholder",
                    "key2", "${placeholder}",
                    "key3", 5
            ));
            Map<String, Object> map = new HashMap<>(Map.of(
                    "another_key", "${another_placeholder}",
                    "inner_key", innerMap,
                    "key", true
            ));
            process(map);
        }
    }

    static class MapWithComplexPlaceholder {
        public static void main(String[] args) {
            Map<String, Object> map = new HashMap<>(Map.of(
                    "key1", "not_placeholder",
                    "key2", "${complex_placeholder}",
                    "key3", 5
            ));
            process(map);
        }
    }

    static class MapWithFormattedComplexPlaceholder {
        public static void main(String[] args) {
            Map<String, Object> map = new HashMap<>(Map.of(
                    "key1", "not_placeholder",
                    "key2", "${complex_placeholder::nw}",
                    "key3", 5
            ));
            process(map);
        }
    }

    static class MapWithList {
        public static void main(String[] args) {
            List<Object> innerList = new ArrayList<>(List.of(
                    "${null_placeholder}",
                    "${complex_placeholder::nw}",
                    5
            ));
            Map<String, Object> innerMap = new HashMap<>(Map.of(
                    "key1", "not_placeholder",
                    "key2", "${complex_placeholder}",
                    "key3", innerList
            ));
            List<Object> list = new ArrayList<>(List.of("${null_placeholder::nw}", innerMap, true));
            Map<String, Object> map = new HashMap<>(Map.of(
                    "key1", "not_placeholder",
                    "key2", "${placeholder}",
                    "key3", list
            ));
            process(map);
        }
    }
}