package al.sankevich;

import al.sankevich.placeholders.constants.ConfigConstants;
import al.sankevich.placeholders.contenttypes.ContentType;
import al.sankevich.placeholders.engines.source.SourceProcessingEngine;
import al.sankevich.placeholders.engines.source.ext.impl.DefaultSourceProcessingEngine;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
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

    private static Function<String, Object> func = placeholder -> {
        String str = sb.append('"').append(placeholder).append('"').toString();
        sb.setLength(0);
        return str;
    };

    public static void main(String[] args) throws IOException {
        try (InputStream is = classloader.getResourceAsStream("test-default.json")) {
            String content = new String(is.readAllBytes());
            System.out.println("start 4");
            Instant start = Instant.now();
            String result = PLACEHOLDER_PROCESSOR.process(content, JSON, func);
            Instant end = Instant.now();
            long current = Duration.between(start, end).toMillis();
            System.out.println(result + "\nTime: " + current + "ms");
        }
    }

    private static void process(String fileName, ContentType type) throws IOException {
        log.info("Processing file {}", fileName);
        try (InputStream is = classloader.getResourceAsStream(fileName)) {
            String content = new String(is.readAllBytes());
            String result = PLACEHOLDER_PROCESSOR.process(content, type, func);
            log.info("{}", content);
            log.info("{}", result);
        }
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
            log.info(".");
            func = (val) -> List.of("a", "b");
            process("test-array.json", JSON);
        }
    }

    static class FormattedArrayJson {
        public static void main(String[] args) throws IOException {
            log.info(".");
            func = (val) -> List.of("a", "b");
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
}