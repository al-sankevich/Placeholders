package al.sankevich.placeholders.dtos;

/**
 * Contains info about position of substring ({@code placeholder}) in the string ({@code source}).
 *
 * @param startIndex the index of the first substring's character in the string.
 * @param endIndex   the index of the last substring's character in the string.
 * @param length     the length of the substring.
 */
public record Position(int startIndex, int endIndex, int length) {

    public static Position of(final int startIndex, final int endIndex) {
        return new Position(startIndex, endIndex, endIndex - startIndex + 1);
    }

    @Override
    public String toString() {
        return "[%d, %d]".formatted(startIndex, endIndex);
    }
}
