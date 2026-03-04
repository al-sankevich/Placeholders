package al.sankevich.placeholders.parsers;

import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.dtos.WrappablePlaceholder;
import al.sankevich.placeholders.parsers.impl.ext.PlainPlaceholdersParser;
import al.sankevich.placeholders.parsers.impl.ext.WrappablePlaceholdersParser;
import al.sankevich.utils.AssertUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class PlainPlaceholdersParserTest {

    private PlaceholderParser<?> parser;

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