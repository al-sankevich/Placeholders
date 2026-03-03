package al.sankevich.placeholders.utils;

public class ExceptionUtils {

    private static final int WINDOW_WIDTH = 40;
    private static final String THREE_DOTS = "...";

    public static String formatExceptionMessage(
            final String component,
            final String message,
            final char[] source,
            final int i,
            final int startIndex,
            final int endIndex
    ) {
        String firstPart = getFirstPart(source, i, startIndex);
        String secondPart = getSecondPart(source, i, endIndex);
        return "%s %s (placeholder position in source is %d)\n%s<<ERROR>>%s ".formatted(
                component,
                message,
                startIndex,
                firstPart,
                secondPart
        );
    }

    private static String getFirstPart(final char[] source, final int i, final int startIndex) {
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

    private static String getSecondPart(final char[] source, final int i, final int endIndex) {
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