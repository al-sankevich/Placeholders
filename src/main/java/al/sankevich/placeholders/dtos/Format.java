package al.sankevich.placeholders.dtos;

public record Format(
        String name,
        String value
) {

    public static Format of(final String name, final String value) {
        return new Format(name, value);
    }

    @Override
    public String toString() {
        return "[%s : %s]".formatted(name, value);
    }
}