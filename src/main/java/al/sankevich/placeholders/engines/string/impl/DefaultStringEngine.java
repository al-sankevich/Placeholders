package al.sankevich.placeholders.engines.string.impl;

import al.sankevich.placeholders.dtos.Position;
import al.sankevich.placeholders.engines.string.StringEngine;

import java.util.stream.IntStream;

/**
 * Default implementation of {@link StringEngine} using {@link StringBuilder}.
 */
public class DefaultStringEngine implements StringEngine {

    private final StringBuilder buffer;
    private int contentLength = 0;

    public DefaultStringEngine(int capacity) {
        this.buffer = new StringBuilder(capacity);
    }

    /**
     * Builds {@code DefaultStringEngine} with empty {@link #buffer}.
     */
    public DefaultStringEngine() {
        this.buffer = new StringBuilder();
    }

    /**
     * Builds {@code DefaultStringEngine} with filled with provided {@code source} {@link #buffer}.
     *
     * @param csq the initial value of the {@link #buffer}
     */
    public DefaultStringEngine(final CharSequence csq) {
        this.contentLength = csq.length();
        this.buffer = new StringBuilder(csq);
    }

    @Override
    public DefaultStringEngine replace(final Position position, final String replacement) {
        int startIndex = position.startIndex();
        int endIndex = position.endIndex();
        int diff = buffer.length() - contentLength;
        buffer.replace(startIndex + diff, endIndex + diff + 1, replacement);
        return this;
    }

    @Override
    public DefaultStringEngine replaceAll(final String target, final String replacement) {
        int idx = buffer.indexOf(target);
        while (idx != -1) {
            buffer.replace(idx, idx + target.length(), replacement);
            idx = buffer.indexOf(target, idx + replacement.length());
        }
        return this;
    }

    @Override
    public DefaultStringEngine addToEnd(final char character) {
        buffer.append(character);
        return this;
    }

    @Override
    public DefaultStringEngine addToStart(final char character) {
        buffer.insert(0, character);
        return this;
    }

    @Override
    public void assign(final CharSequence csq) {
        contentLength = csq.length();
        buffer.setLength(0);
        buffer.append(csq);
    }

    @Override
    public void clear() {
        contentLength = 0;
        buffer.setLength(0);
    }

    @Override
    public String toStringAndClear() {
        String temp = toString();
        clear();
        return temp;
    }

    @Override
    public int length() {
        return buffer.length();
    }

    @Override
    public char charAt(final int index) {
        return buffer.charAt(index);
    }

    @Override
    public boolean isEmpty() {
        return buffer.isEmpty();
    }

    @Override
    public CharSequence subSequence(final int start, final int end) {
        return buffer.subSequence(start, end);
    }

    @Override
    public String toString() {
        return buffer.toString();
    }

    @Override
    public IntStream chars() {
        return buffer.chars();
    }

    @Override
    public IntStream codePoints() {
        return buffer.codePoints();
    }

    @Override
    public DefaultStringEngine append(final CharSequence csq) {
        buffer.append(csq);
        return this;
    }

    @Override
    public DefaultStringEngine append(final CharSequence csq, final int start, final int end) {
        buffer.append(csq, start, end);
        return this;
    }

    @Override
    public DefaultStringEngine append(final char c) {
        buffer.append(c);
        return this;
    }
}
