package al.sankevich.placeholders.utils;

import al.sankevich.placeholders.dtos.Component;
import al.sankevich.placeholders.dtos.ExceptionInfo;

import java.util.Arrays;

public class ExceptionUtils {

    private static final int WINDOW_WIDTH = 40;
    private static final String THREE_DOTS = "...";

    public static String formatNotReservedCharacterEscapedExceptionMessage(final ExceptionInfo info) {
        Component component = info.component();
        return formatExceptionMessage(
                component.name(),
                "contains escaped not reserved character (reserved: %s)".formatted(
                        Arrays.toString(component.endingChars())
                ),
                info.source(),
                info.i() + 1,
                info.startIndex(),
                info.endIndex()
        );
    }

    public static String formatEmptyComponentExceptionMessage(final ExceptionInfo info) {
        return formatExceptionMessage(
                info.component().name(),
                "is empty",
                info.source(),
                info.i(),
                info.startIndex(),
                info.endIndex()
        );
    }

    public static String formatExceptionMessage(
            final String component,
            final String reason,
            final String source,
            final int pivot,
            final int startIndex,
            final int endIndex
    ) {
        char[] sourceChars = source.toCharArray();
        String left = getLeft(sourceChars, pivot, startIndex);
        String right = getRight(sourceChars, pivot, endIndex);
        return "%s %s (placeholder position in source is %d)\n%s<<ERROR>>%s".formatted(
                component,
                reason,
                startIndex,
                left,
                right
        );
    }

    private static String getLeft(final char[] source, final int i, final int startIndex) {
        int windowStartIndex = startIndex < WINDOW_WIDTH ? 0 : startIndex - WINDOW_WIDTH;
        int length = i - windowStartIndex;

        if (windowStartIndex == 0) {
            return new String(source, windowStartIndex, length);
        } else {
            char[] result = new char[THREE_DOTS.length() + length];
            THREE_DOTS.getChars(0, THREE_DOTS.length(), result, 0);
            System.arraycopy(source, windowStartIndex, result, THREE_DOTS.length(), length);
            return new String(result);
        }
    }

    private static String getRight(final char[] source, final int i, final int endIndex) {
        int lastIndex = source.length - 1;
        int windowEndIndex = endIndex < lastIndex - WINDOW_WIDTH ? endIndex + WINDOW_WIDTH : lastIndex;
        int length = windowEndIndex - i + 1;

        if (windowEndIndex == lastIndex) {
            return new String(source, i, length);
        } else {
            char[] result = new char[length + THREE_DOTS.length()];
            System.arraycopy(source, i, result, 0, length);
            THREE_DOTS.getChars(0, THREE_DOTS.length(), result, length);
            return new String(result);
        }
    }
}