package al.sankevich.placeholders.parsers;

import al.sankevich.placeholders.dtos.Placeholder;

/**
 * Implementations of this interface are responsible for parsing {@link T placeholders} from {@code source}.
 *
 * @param <T> the expected placeholder type.
 */
public interface PlaceholderParser<T extends Placeholder> {

    /**
     * Parses next {@link T placeholder} from {@code source}.
     *
     * @return next parsed {@link T placeholder} from {@code source}, otherwise {@code null}.
     */
    T parse();
}
