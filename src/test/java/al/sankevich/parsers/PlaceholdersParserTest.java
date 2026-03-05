package al.sankevich.parsers;

import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.dtos.WrappablePlaceholder;
import al.sankevich.placeholders.exceptions.PlaceholderParsingException;
import al.sankevich.placeholders.parsers.PlaceholderParser;
import al.sankevich.placeholders.parsers.impl.ext.PlainPlaceholdersParser;
import al.sankevich.placeholders.parsers.impl.ext.WrappablePlaceholdersParser;
import al.sankevich.utils.AssertUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlaceholdersParserTest {

    @ParameterizedTest
    @MethodSource({"al.sankevich.providers.ParserArgumentsProvider#providePlainCorrect"})
    public void plainParserTest_Success(final String source, final Placeholder expected) {
        PlaceholderParser<Placeholder> parser = setUpPlain(source);

        AssertUtils.assertEquals(expected, parser.parse());
    }

    @ParameterizedTest
    @MethodSource({"al.sankevich.providers.ParserArgumentsProvider#provideJsonCorrect"})
    public void jsonParserTest_Success(final String source, final WrappablePlaceholder expected) {
        PlaceholderParser<WrappablePlaceholder> parser = setUpJson(source);

        AssertUtils.assertEquals(expected, parser.parse());
    }

    @ParameterizedTest
    @MethodSource({"al.sankevich.providers.ParserArgumentsProvider#provideJsonIncorrect"})
    public void plainParserTest_Fail(final String source, final String expected) {
        PlaceholderParser<Placeholder> parser = setUpPlain(source);

        PlaceholderParsingException e = assertThrows(PlaceholderParsingException.class, parser::parse);
        assertEquals(expected, e.getMessage());
    }

    @ParameterizedTest
    @MethodSource({"al.sankevich.providers.ParserArgumentsProvider#provideJsonIncorrect"})
    public void jsonParserTest_Fail(final String source, final String expected) {
        PlaceholderParser<WrappablePlaceholder> parser = setUpJson(source);

        PlaceholderParsingException e = assertThrows(PlaceholderParsingException.class, parser::parse);
        assertEquals(expected, e.getMessage());
    }

    private PlaceholderParser<Placeholder> setUpPlain(final String source) {
        return new PlainPlaceholdersParser(source);
    }

    private PlaceholderParser<WrappablePlaceholder> setUpJson(final String source) {
        return setUpWrappable(source, '"');
    }

    private PlaceholderParser<WrappablePlaceholder> setUpSql(final String source) {
        return setUpWrappable(source, '\'');
    }

    private PlaceholderParser<WrappablePlaceholder> setUpWrappable(final String source, final char wrapper) {
        return new WrappablePlaceholdersParser(source, wrapper);
    }
}