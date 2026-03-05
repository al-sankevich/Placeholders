package al.sankevich.placeholders.dtos;

public record ExceptionInfo(
        Component component,
        String source,
        int i,
        int startIndex,
        int endIndex
) {
}
