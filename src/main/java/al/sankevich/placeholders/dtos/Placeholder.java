package al.sankevich.placeholders.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Represents the parsed {@code placeholder} and contains it's main info.
 */
@Getter
@Builder
@RequiredArgsConstructor
public class Placeholder {

    /**
     * Placeholder's name.
     */
    private final String name;
    /**
     * {@code Formats} info, enabled for this {@code placeholder} ([key, value]).
     */
    private final List<String[]> enabledFormats;
    /**
     * {@code Formats} names, disabled for this {@code placeholder}.
     */
    private final Set<String> disabledFormats;
    /**
     * The {@code position} of this {@code placeholder} in the {@code source}, where it was parsed.
     */
    private final Position position;

    @Override
    public String toString() {
        return "Placeholder name: %s, position: %s, enabled formats: %s, disabled formats: %s"
                .formatted(
                        name, position, enabledFormats.stream()
                                .map(Arrays::toString)
                                .toList()
                                .toString(), disabledFormats
                );
    }

}
