package al.sankevich.utils;

import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.dtos.Position;
import al.sankevich.placeholders.dtos.WrappablePlaceholder;

import java.util.List;
import java.util.Set;

import static al.sankevich.utils.FormatUtils.F1_V_ESCAPED;

public class PlaceholderUtils {

    private static final int defaultStartIndex = 14;
    private static final int defaultEndIndex = 27;
    private static final String defaultPlaceholderName = "placeholder";
    private static final String defaultEscapedPlaceholderName = "placeholder^}:";

    private static int countEnabledFormatsLength(final List<String[]> enabledFormats) {
        if (enabledFormats != null && !enabledFormats.isEmpty()) {
            int length = enabledFormats.size() - 1; // ,

            for (String[] enabledFormat : enabledFormats) {

                length += enabledFormat[0].length();

                if (enabledFormat[1] != null) {
                    length += 1 + enabledFormat[1].length(); // = + name
                }
            }

            return length;
        }

        return 0;
    }

    private static int countDisabledFormatsLength(final Set<String> disabledFormats) {
        if (disabledFormats != null && !disabledFormats.isEmpty()) {
            int length = disabledFormats.size() - 1;

            for (String disabledFormat : disabledFormats) {
                length += disabledFormat.length();
            }

            return length;
        }

        return 0;
    }

    public static int countEndIndex(final List<String[]> enabledFormats, final Set<String> disabledFormats) {
        return countEndIndex(defaultPlaceholderName, enabledFormats, disabledFormats);
    }

    public static int countEndIndex(
            final String placeholderName,
            final List<String[]> enabledFormats,
            final Set<String> disabledFormats
    ) {
        int enabledFormatsLength = countEnabledFormatsLength(enabledFormats);
        int disabledFormatsLength = countDisabledFormatsLength(disabledFormats);
        int lastIndex = defaultStartIndex + 2 + placeholderName.length() - 1;

        if (enabledFormatsLength == 0 && disabledFormatsLength != 0) { // ${placeholder::f1}
            return lastIndex + 2 + disabledFormatsLength + 1;
        }
        if (enabledFormatsLength != 0) { // ${placeholder:f1}
            lastIndex += 1 + enabledFormatsLength;
        }
        if (disabledFormatsLength != 0) { // ${placeholder:f1:f1}
            lastIndex += 1 + disabledFormatsLength;
        }

        return lastIndex + 1;
    }

    public static class Plain {

        public static Placeholder buildEscaped() {
            return Placeholder.builder()
                    .name(defaultEscapedPlaceholderName)
                    .enabledFormats(List.<String[]>of(F1_V_ESCAPED))
                    .disabledFormats(Set.of(F1_V_ESCAPED[1]))
                    .position(Position.of(14, 68))
                    .build();
        }

        public static Placeholder build() {
            return builder().build();
        }

        public static Placeholder build(final String[] enabledFormat) {
            return build(List.<String[]>of(enabledFormat));
        }

        public static Placeholder build(final List<String[]> enabledFormats) {
            int lastIndex = countEndIndex(enabledFormats, null);
            return builder().enabledFormats(enabledFormats)
                    .position(Position.of(defaultStartIndex, lastIndex))
                    .build();
        }

        public static Placeholder build(final String disabledFormat) {
            return build(Set.of(disabledFormat));
        }

        public static Placeholder build(final Set<String> disabledFormats) {
            int lastIndex = countEndIndex(null, disabledFormats);
            return builder().disabledFormats(disabledFormats)
                    .position(Position.of(defaultStartIndex, lastIndex))
                    .build();
        }

        public static Placeholder build(final List<String[]> enabledFormats, final Set<String> disabledFormats) {
            return builder().enabledFormats(enabledFormats).disabledFormats(disabledFormats).build();
        }

        private static Placeholder.PlaceholderBuilder<?, ?> builder() {
            return Placeholder.builder()
                    .name(defaultPlaceholderName)
                    .enabledFormats(List.of())
                    .disabledFormats(Set.of())
                    .position(Position.of(defaultStartIndex, 27));
        }
    }

    public static class Wrappable {

        public static WrappablePlaceholder build(final char wrapper, final boolean isWrapped) {
            return builder(wrapper, isWrapped).build();
        }

        public static WrappablePlaceholder build(
                final char wrapper,
                final boolean isWrapped,
                final String[] enabledFormat
        ) {
            return build(wrapper, isWrapped, List.<String[]>of(enabledFormat));
        }

        public static WrappablePlaceholder build(
                final char wrapper,
                final boolean isWrapped,
                final List<String[]> enabledFormats
        ) {
            int endIndex = countEndIndex(enabledFormats, null);
            return builder(wrapper, isWrapped)
                    .enabledFormats(enabledFormats)
                    .position(getPosition(isWrapped, defaultStartIndex, endIndex))
                    .build();
        }

        public static WrappablePlaceholder build(
                final char wrapper,
                final boolean isWrapped,
                final String disabledFormat
        ) {
            return build(wrapper, isWrapped, Set.of(disabledFormat));
        }

        public static WrappablePlaceholder build(
                final char wrapper,
                final boolean isWrapped,
                final Set<String> disabledFormats
        ) {
            int endIndex = countEndIndex(null, disabledFormats);
            return builder(wrapper, isWrapped)
                    .disabledFormats(disabledFormats)
                    .position(getPosition(isWrapped, defaultStartIndex, endIndex))
                    .build();
        }

        public static WrappablePlaceholder build(
                final char wrapper,
                final boolean isWrapped,
                final List<String[]> enabledFormats,
                final Set<String> disabledFormats
        ) {
            return builder(wrapper, isWrapped).enabledFormats(enabledFormats).disabledFormats(disabledFormats).build();
        }

        private static WrappablePlaceholder.WrappablePlaceholderBuilder<?, ?> builder(
                final char wrapper,
                final boolean isWrapped
        ) {
            return WrappablePlaceholder.builder()
                    .name(defaultPlaceholderName)
                    .enabledFormats(List.of())
                    .disabledFormats(Set.of())
                    .wrapper(wrapper)
                    .position(getPosition(isWrapped, defaultStartIndex, defaultEndIndex));
        }

        public static Position getPosition(final boolean isWrapped, final int startIndex, final int endIndex) {
            return Position.of(isWrapped ? startIndex - 1 : startIndex, isWrapped ? endIndex + 1 : endIndex);
        }

        public static class Json {

            private static final char wrapper = '"';

            public static WrappablePlaceholder build(final boolean isWrapped) {
                return Wrappable.build(wrapper, isWrapped);
            }

            public static WrappablePlaceholder build(final boolean isWrapped, final String[] enabledFormat) {
                return Wrappable.build(wrapper, isWrapped, enabledFormat);
            }

            public static WrappablePlaceholder build(final boolean isWrapped, final List<String[]> enabledFormats) {
                return Wrappable.build(wrapper, isWrapped, enabledFormats);
            }

            public static WrappablePlaceholder build(final boolean isWrapped, final String disabledFormat) {
                return Wrappable.build(wrapper, isWrapped, disabledFormat);
            }

            public static WrappablePlaceholder build(final boolean isWrapped, final Set<String> disabledFormats) {

                return Wrappable.build(wrapper, isWrapped, disabledFormats);
            }

            public static WrappablePlaceholder build(
                    final boolean isWrapped,
                    final List<String[]> enabledFormats,
                    final Set<String> disabledFormats
            ) {
                return Wrappable.build(wrapper, isWrapped, enabledFormats, disabledFormats);
            }
        }
    }
}
