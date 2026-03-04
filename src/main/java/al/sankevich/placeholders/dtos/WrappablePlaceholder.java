package al.sankevich.placeholders.dtos;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

/**
 * Represents the parsed {@code placeholder}, which can be wrapped with single character from both sides.
 */
@Getter
@SuperBuilder
public class WrappablePlaceholder extends Placeholder {

    /**
     * The wrapping symbol of this {@code placeholder}. Example: ' or "
     */
    private final char wrapper;

    public WrappablePlaceholder(
            final String name,
            final char wrapper,
            final Position position,
            final List<String[]> enabledFormats,
            final Set<String> disabledFormats
    ) {
        super(name, enabledFormats, disabledFormats, position);
        this.wrapper = wrapper;
    }

    @Override
    public String toString() {
        return "%s, delimiter: %c".formatted(super.toString(), wrapper);
    }

}
