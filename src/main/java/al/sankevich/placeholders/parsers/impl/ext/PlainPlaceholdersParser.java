package al.sankevich.placeholders.parsers.impl.ext;

import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.dtos.Position;
import al.sankevich.placeholders.parsers.impl.AbstractPlaceholdersParser;

import java.util.List;
import java.util.Set;

/**
 * {@link AbstractPlaceholdersParser}'s implementation for parsing placeholders of {@link Placeholder} type.
 */
public class PlainPlaceholdersParser extends AbstractPlaceholdersParser<Placeholder> {

    public PlainPlaceholdersParser(String source) {
        super(source);
    }

    @Override
    protected Placeholder buildPlaceholder(
            final String source,
            final String placeholderName,
            final List<String[]> enabledFormats,
            final Set<String> disabledFormats,
            final int startIndex,
            final int endIndex
    ) {
        return Placeholder.builder()
                .name(placeholderName)
                .enabledFormats(enabledFormats)
                .disabledFormats(disabledFormats)
                .position(Position.of(startIndex, endIndex))
                .build();
    }
}
