package al.sankevich.benchmark.core.utils;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.Objects;

public class FileUtils {

    @SneakyThrows
    public static String loadFile(final String filename) {
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename)) {
            return new String(assertIsReadable(is, filename).readAllBytes());
        }
    }

    private static InputStream assertIsReadable(final InputStream is, final String filename) {
        return Objects.requireNonNull(is, "Unable to read file from resource: " + filename);
    }
}
