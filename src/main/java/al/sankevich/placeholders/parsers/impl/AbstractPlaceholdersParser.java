package al.sankevich.placeholders.parsers.impl;

import al.sankevich.placeholders.dtos.Component;
import al.sankevich.placeholders.dtos.ExceptionInfo;
import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.engines.string.StringEngine;
import al.sankevich.placeholders.engines.string.impl.DefaultStringEngine;
import al.sankevich.placeholders.exceptions.EmptyComponentException;
import al.sankevich.placeholders.exceptions.NotReservedCharacterEscapedException;
import al.sankevich.placeholders.parsers.PlaceholderParser;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static al.sankevich.placeholders.constants.ParsingConstants.DISABLED_FORMAT_NAME_COMPONENT;
import static al.sankevich.placeholders.constants.ParsingConstants.ENABLED_FORMAT_NAME_COMPONENT;
import static al.sankevich.placeholders.constants.ParsingConstants.ENABLED_FORMAT_VALUE_COMPONENT;
import static al.sankevich.placeholders.constants.ParsingConstants.ESCAPING_SYMBOL;
import static al.sankevich.placeholders.constants.ParsingConstants.FORMATS_SEPARATING_SYMBOL;
import static al.sankevich.placeholders.constants.ParsingConstants.FORMAT_SEPARATING_SYMBOL;
import static al.sankevich.placeholders.constants.ParsingConstants.PLACEHOLDER_LEFT_BORDER_SYMBOL;
import static al.sankevich.placeholders.constants.ParsingConstants.PLACEHOLDER_NAME_COMPONENT;
import static al.sankevich.placeholders.constants.ParsingConstants.PLACEHOLDER_RIGHT_BORDER_SYMBOL;
import static al.sankevich.placeholders.constants.ParsingConstants.PLACEHOLDER_STARTING_SYMBOL;
import static al.sankevich.placeholders.constants.ParsingConstants.SECTIONS_SEPARATING_SYMBOL;

/**
 * General implementation of {@link PlaceholderParser}.
 *
 * @param <T> the expected placeholder type.
 */
public abstract class AbstractPlaceholdersParser<T extends Placeholder> implements PlaceholderParser<T> {

    private final StringEngine buffer = new DefaultStringEngine();
    private final String source;
    boolean isEscaped = false;
    private int i = 0;
    private int j = 0;
    private int startIndex = 0;
    private int endIndex = 0;

    public AbstractPlaceholdersParser(final String source) {
        this.source = source;
    }

    @Override
    public T parse() {
        for (; i < length(); ++i) {

            if (isPlaceholderStarts()) {

                startIndex = i;

                j = i += 2; // skips ${

                searchPlaceholderOrFileEnd(); // places j-pointer at placeholder or file end

                if (isPlaceholderEnd()) {

                    endIndex = j;

                    String placeholderName = searchPlaceholderName();

                    ++i;

                    List<String[]> enabledFormats = searchEnabledFormats();
                    Set<String> disabledFormats = searchDisabledFormats();

                    i = j + 1;

                    return buildPlaceholder(
                            source,
                            placeholderName,
                            enabledFormats,
                            disabledFormats,
                            startIndex,
                            endIndex
                    );
                }

                i = j + 1;
            }
        }

        return null;
    }

    /**
     * Builds {@link T placeholder} from provided parameters.
     *
     * @param source          the source, from which this placeholder was parsed.
     * @param placeholderName the parsed placeholder's name.
     * @param enabledFormats  the parsed placeholder's enabled formats.
     * @param disabledFormats the parsed placeholder's disabled formats names.
     * @param startIndex      the index in {@code source}, where parsed placeholder starts.
     * @param endIndex        the index in {@code source}, where parsed placeholder ends.
     * @return parsed {@link T placeholder}.
     */
    protected abstract T buildPlaceholder(
            String source,
            String placeholderName,
            List<String[]> enabledFormats,
            Set<String> disabledFormats,
            int startIndex,
            int endIndex
    );

    private boolean isPlaceholderStarts() {
        int nextPoint = i + 1;
        return charAt(i) == PLACEHOLDER_STARTING_SYMBOL &&
                nextPoint < length() && charAt(nextPoint) == PLACEHOLDER_LEFT_BORDER_SYMBOL;
    }

    private void searchPlaceholderOrFileEnd() {
        while (j < length() && (charAt(j) != PLACEHOLDER_RIGHT_BORDER_SYMBOL || isEscaped)) {
            updateIsEscaped(j++);
        }
    }

    private boolean isPlaceholderEnd() {
        return j < length() && charAt(j) == PLACEHOLDER_RIGHT_BORDER_SYMBOL && !isEscaped;
    }

    private String searchPlaceholderName() {
        return searchComponent(PLACEHOLDER_NAME_COMPONENT);
    }

    private List<String[]> searchEnabledFormats() {
        if (isSectionSkippable()) {
            ++i;
            return List.of();
        }

        List<String[]> formats = new ArrayList<>();

        while (i < j) {
            formats.add(searchEnabledFormat());
            ++i;
            if (charAt(i - 1) == SECTIONS_SEPARATING_SYMBOL) {
                break;
            }
        }

        checkIfEndedWithComma(ENABLED_FORMAT_NAME_COMPONENT);

        return formats;
    }

    private Set<String> searchDisabledFormats() {
        Set<String> formats = new HashSet<>();

        if (isSectionSkippable()) {
            ++i;
            return formats;
        }

        while (i < j) {
            formats.add(searchDisabledFormatName());
            ++i;
        }

        checkIfEndedWithComma(DISABLED_FORMAT_NAME_COMPONENT);

        return formats;
    }

    private boolean isSectionSkippable() {
        return i < j && charAt(i) == SECTIONS_SEPARATING_SYMBOL;
    }

    private void checkIfEndedWithComma(final Component component) {
        if (source.charAt(i - 1) == FORMATS_SEPARATING_SYMBOL) {
            throw emptyComponentException(component);
        }
    }

    private String[] searchEnabledFormat() {
        String formatName = searchEnabledFormatName();
        String formatValue = searchEnabledFormatValue();

        return new String[] {formatName, formatValue};
    }


    private String searchEnabledFormatName() {
        return searchComponent(ENABLED_FORMAT_NAME_COMPONENT);
    }

    private String searchEnabledFormatValue() {
        String formatValue = null;

        if (charAt(i) == FORMAT_SEPARATING_SYMBOL) {
            ++i;
            formatValue = searchComponent(ENABLED_FORMAT_VALUE_COMPONENT);
        }

        return formatValue;
    }

    private String searchDisabledFormatName() {
        return searchComponent(DISABLED_FORMAT_NAME_COMPONENT);
    }

    private String searchComponent(final Component component) {
        String formatValue = obtainValue(component);

        verifyIsNotEmpty(component, formatValue);

        return formatValue;
    }

    @SneakyThrows
    private String obtainValue(final Component component) {
        while (i < j && (isNotEndOfComponent(i, component) || isEscaped)) {
            if (!updateIsEscaped(i)) {
                buffer.append(charAt(i));
            } else {
                assertIsNextReserved(component);
            }
            ++i;
        }

        isEscaped = false;

        return buffer.toStringAndClear();
    }

    private boolean isNotReserved(final int pointer, final Component component) {
        return charAt(pointer) != ESCAPING_SYMBOL && charAt(pointer) != PLACEHOLDER_RIGHT_BORDER_SYMBOL &&
                isNotEndOfComponent(pointer, component);
    }

    private boolean isNotEndOfComponent(final int pointer, final Component component) {
        return component.isNotEnd(charAt(pointer));
    }

    private boolean updateIsEscaped(final int pointer) {
        if (charAt(pointer) == ESCAPING_SYMBOL) {
            return isEscaped = !isEscaped;
        }

        return isEscaped = false;
    }

    private void verifyIsNotEmpty(final Component component, final String value) {
        if (value.isEmpty()) {
            throw emptyComponentException(component);
        }
    }

    private void assertIsNextReserved(final Component component) {
        if (isNotReserved(i + 1, component)) {
            throw notReservedCharacterEscapedException(component);
        }
    }

    private char charAt(final int pointer) {
        return source.charAt(pointer);
    }

    private int length() {
        return source.length();
    }

    private EmptyComponentException emptyComponentException(final Component component) {
        return EmptyComponentException.of(exceptionInfo(component));
    }

    private NotReservedCharacterEscapedException notReservedCharacterEscapedException(final Component component) {
        return NotReservedCharacterEscapedException.of(exceptionInfo(component));
    }

    private ExceptionInfo exceptionInfo(final Component component) {
        return new ExceptionInfo(component, source, i, startIndex, endIndex);
    }
}
