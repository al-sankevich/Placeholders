package al.sankevich.placeholders.mappers.impl;

import al.sankevich.placeholders.constants.ProcessingConstants;
import al.sankevich.placeholders.mappers.ValueMapper;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Map;

/**
 * Default implementation of {@link ValueMapper}.
 */
public class DefaultValueMapper implements ValueMapper {

    /**
     * {@inheritDoc}
     * {@code value} of type {@link List} and {@link Map} will be mapped to {@code JSON} format.
     * Other types of {@code value} will be mapped to their {@link String#valueOf default} representations.
     *
     * @param value placeholder's replacement value.
     * @return mapped {@code value} to {@link String}.
     */
    @Override
    @SneakyThrows
    public String stringify(final Object value) {
        return isMapOrList(value) ? ProcessingConstants.OBJECT_MAPPER.writeValueAsString(value) : String.valueOf(value);
    }

    private boolean isMapOrList(final Object value) {
        return value instanceof Map || value instanceof List;
    }

}
