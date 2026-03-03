package al.sankevich.placeholders.mappers;

/**
 * Implementation of this interface are responsible for mapping placeholder's replacement value to other types.
 */
public interface ValueMapper {

    /**
     * Maps provided parameter {@code value} to {@link String}.
     *
     * @param value placeholder's replacement value.
     * @return mapped {@code value} to {@link String}.
     */
    String stringify(Object value);

}
