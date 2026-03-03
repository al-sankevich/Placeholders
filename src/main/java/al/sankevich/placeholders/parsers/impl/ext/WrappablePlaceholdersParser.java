package al.sankevich.placeholders.parsers.impl.ext;

import al.sankevich.placeholders.dtos.Position;
import al.sankevich.placeholders.dtos.WrappablePlaceholder;
import al.sankevich.placeholders.parsers.impl.AbstractPlaceholdersParser;

import java.util.List;
import java.util.Set;

import static al.sankevich.placeholders.constants.FormatingConstants.NO_ESCAPE;
import static al.sankevich.placeholders.constants.FormatingConstants.NO_WRAP;

/**
 * {@link AbstractPlaceholdersParser}'s implementation for parsing placeholders of {@link WrappablePlaceholder} type.
 */
public class WrappablePlaceholdersParser extends AbstractPlaceholdersParser<WrappablePlaceholder> {

    private final char delimiter;

    public WrappablePlaceholdersParser(final String source, final char delimiter) {
        super(source);
        this.delimiter = delimiter;
    }

    @Override
    protected WrappablePlaceholder buildPlaceholder(
            final String source,
            final String placeholderName,
            final List<String[]> enabledFormats,
            final Set<String> disabledFormats,
            final int startIndex,
            final int endIndex
    ) {
        int tempStartIndex = startIndex;
        int tempEndIndex = endIndex;

        if (disabledFormats.contains(NO_WRAP)) {
            disabledFormats.add(NO_ESCAPE);
        }

        if (startIndex > 0 && endIndex < source.length() - 1 &&
                source.charAt(startIndex - 1) == delimiter &&
                source.charAt(endIndex + 1) == delimiter) {
            --tempStartIndex;
            ++tempEndIndex;
        } else {
            disabledFormats.add(NO_WRAP);
        }

        return new WrappablePlaceholder(
                placeholderName,
                delimiter,
                Position.of(tempStartIndex, tempEndIndex),
                enabledFormats,
                disabledFormats
        );
    }
}
