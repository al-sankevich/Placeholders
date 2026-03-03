package al.sankevich.placeholders.utils;

import al.sankevich.placeholders.engines.string.StringEngine;

public class FormatUtils {

    private static final char[] HEX = "0123456789abcdef".toCharArray();

    public static void quoteJson(final StringEngine buffer) {
        if (buffer != null && !buffer.isEmpty()) {

            int len = buffer.length();
            StringBuilder sb = new StringBuilder((int) (len * 1.5));

            for (int i = 0; i < len; ++i) {
                appendReplacement(buffer, sb, i);
            }

            buffer.assign(sb);
        }
    }

    private static void appendReplacement(
            final StringEngine buffer,
            final StringBuilder sb,
            final int i
    ) {
        char ch;
        String t;
        switch (ch = buffer.charAt(i)) {
            case '\\' -> sb.append("\\\\");
            case '"' -> sb.append("\\\"");
            case '/' -> {
                if (i > 0 && buffer.charAt(i - 1) == '<') {
                    sb.append('\\');
                }
                sb.append(ch);
            }
            case '\b' -> sb.append("\\b");
            case '\t' -> sb.append("\\t");
            case '\n' -> sb.append("\\n");
            case '\f' -> sb.append("\\f");
            case '\r' -> sb.append("\\r");
            case '\u2028' -> sb.append("\\u2028");
            case '\u2029' -> sb.append("\\u2029");
            default -> {
                if (ch < ' ') {
                    sb.append("\\u00").append(HEX[(ch >> 4) & 0xF]).append(HEX[ch & 0xF]);
                } else {
                    sb.append(ch);
                }
            }
        }
    }

    public static void quoteSql(final StringEngine buffer) {
        buffer.replaceAll("'", "''");
    }

}
