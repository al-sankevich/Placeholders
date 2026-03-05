package al.sankevich.providers;

import al.sankevich.utils.PlaceholderUtils;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static al.sankevich.utils.ExceptionUtils.EMPTY_DISABLED_FORMAT_NAME_AFTER_COMMA;
import static al.sankevich.utils.ExceptionUtils.EMPTY_DISABLED_FORMAT_NAME_BEFORE_COMMA_MESSAGE;
import static al.sankevich.utils.ExceptionUtils.EMPTY_ENABLED_FORMAT_NAME_AFTER_COMMA_MESSAGE;
import static al.sankevich.utils.ExceptionUtils.EMPTY_ENABLED_FORMAT_NAME_BEFORE_COMMA_MESSAGE;
import static al.sankevich.utils.ExceptionUtils.EMPTY_ENABLED_FORMAT_NAME_MESSAGE;
import static al.sankevich.utils.ExceptionUtils.EMPTY_ENABLED_FORMAT_VALUE_MESSAGE;
import static al.sankevich.utils.ExceptionUtils.EMPTY_PLACEHOLDER_NAME_MESSAGE;
import static al.sankevich.utils.FormatUtils.F1;
import static al.sankevich.utils.FormatUtils.F1_V;
import static al.sankevich.utils.FormatUtils.F2;
import static al.sankevich.utils.FormatUtils.F2_V;
import static al.sankevich.utils.SourceUtils.SIMPLE_SOURCE;
import static al.sankevich.utils.SourceUtils.SOURCE_WITHOUT_DISABLED_FORMAT_NAME_AFTER_COMMA;
import static al.sankevich.utils.SourceUtils.SOURCE_WITHOUT_DISABLED_FORMAT_NAME_BEFORE_COMMA;
import static al.sankevich.utils.SourceUtils.SOURCE_WITHOUT_ENABLED_FORMAT_NAME_AFTER_COMMA;
import static al.sankevich.utils.SourceUtils.SOURCE_WITHOUT_ENABLED_FORMAT_NAME_BEFORE_COMMA;
import static al.sankevich.utils.SourceUtils.SOURCE_WITHOUT_ENABLED_FORMAT_NAME_BEFORE_VALUE;
import static al.sankevich.utils.SourceUtils.SOURCE_WITHOUT_ENABLED_FORMAT_VALUE_AFTER_NAME;
import static al.sankevich.utils.SourceUtils.SOURCE_WITHOUT_PLACEHOLDER_NAME;
import static al.sankevich.utils.SourceUtils.SOURCE_WITH_DISABLED_FORMAT;
import static al.sankevich.utils.SourceUtils.SOURCE_WITH_DISABLED_FORMATS;
import static al.sankevich.utils.SourceUtils.SOURCE_WITH_ENABLED_FORMAT;
import static al.sankevich.utils.SourceUtils.SOURCE_WITH_ENABLED_FORMATS;
import static al.sankevich.utils.SourceUtils.SOURCE_WITH_ENABLED_FORMATS_WITH_VALUES;
import static al.sankevich.utils.SourceUtils.SOURCE_WITH_ENABLED_FORMAT_WITH_VALUE;

public class ParserArgumentsProvider {

    public static Stream<Arguments> providePlainCorrect() {
        return Stream.of(
                Arguments.of(SIMPLE_SOURCE, PlaceholderUtils.Plain.build()),
                Arguments.of(SOURCE_WITH_ENABLED_FORMAT, PlaceholderUtils.Plain.build(F1)),
                Arguments.of(SOURCE_WITH_ENABLED_FORMAT_WITH_VALUE, PlaceholderUtils.Plain.build(F1_V)),
                Arguments.of(SOURCE_WITH_ENABLED_FORMATS, PlaceholderUtils.Plain.build(List.of(F1, F2))),
                Arguments.of(
                        SOURCE_WITH_ENABLED_FORMATS_WITH_VALUES,
                        PlaceholderUtils.Plain.build(List.of(F1_V, F2_V))
                ),
                Arguments.of(SOURCE_WITH_DISABLED_FORMAT, PlaceholderUtils.Plain.build(F1[0])),
                Arguments.of(SOURCE_WITH_DISABLED_FORMATS, PlaceholderUtils.Plain.build(Set.of(F1[0], F2[0])))
        );
    }

    public static Stream<Arguments> provideJsonCorrect() {
        return Stream.of(
                Arguments.of(SIMPLE_SOURCE, PlaceholderUtils.Wrappable.Json.build(true)),
                Arguments.of(SOURCE_WITH_ENABLED_FORMAT, PlaceholderUtils.Wrappable.Json.build(true, F1)),
                Arguments.of(
                        SOURCE_WITH_ENABLED_FORMAT_WITH_VALUE,
                        PlaceholderUtils.Wrappable.Json.build(true, F1_V)
                ),
                Arguments.of(
                        SOURCE_WITH_ENABLED_FORMATS,
                        PlaceholderUtils.Wrappable.Json.build(true, List.of(F1, F2))
                ),
                Arguments.of(
                        SOURCE_WITH_ENABLED_FORMATS_WITH_VALUES,
                        PlaceholderUtils.Wrappable.Json.build(true, List.of(F1_V, F2_V))
                ),
                Arguments.of(
                        SOURCE_WITH_DISABLED_FORMAT,
                        PlaceholderUtils.Wrappable.Json.build(true, F1[0])
                ),
                Arguments.of(
                        SOURCE_WITH_DISABLED_FORMATS,
                        PlaceholderUtils.Wrappable.Json.build(true, Set.of(F1[0], F2[0]))
                )
        );
    }

    public static Stream<Arguments> provideIncorrect() {
        return Stream.of(
                Arguments.of(
                        SOURCE_WITHOUT_PLACEHOLDER_NAME,
                        EMPTY_PLACEHOLDER_NAME_MESSAGE
                ),
                Arguments.of(
                        SOURCE_WITHOUT_ENABLED_FORMAT_NAME_BEFORE_VALUE,
                        EMPTY_ENABLED_FORMAT_NAME_MESSAGE
                ),
                Arguments.of(
                        SOURCE_WITHOUT_ENABLED_FORMAT_VALUE_AFTER_NAME,
                        EMPTY_ENABLED_FORMAT_VALUE_MESSAGE
                ),
                Arguments.of(
                        SOURCE_WITHOUT_ENABLED_FORMAT_NAME_BEFORE_COMMA,
                        EMPTY_ENABLED_FORMAT_NAME_BEFORE_COMMA_MESSAGE
                ),
                Arguments.of(
                        SOURCE_WITHOUT_ENABLED_FORMAT_NAME_AFTER_COMMA,
                        EMPTY_ENABLED_FORMAT_NAME_AFTER_COMMA_MESSAGE
                ),
                Arguments.of(
                        SOURCE_WITHOUT_DISABLED_FORMAT_NAME_BEFORE_COMMA,
                        EMPTY_DISABLED_FORMAT_NAME_BEFORE_COMMA_MESSAGE
                ),
                Arguments.of(
                        SOURCE_WITHOUT_DISABLED_FORMAT_NAME_AFTER_COMMA,
                        EMPTY_DISABLED_FORMAT_NAME_AFTER_COMMA
                )
        );
    }
}
