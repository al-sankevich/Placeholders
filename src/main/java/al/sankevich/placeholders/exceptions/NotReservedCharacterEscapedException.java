package al.sankevich.placeholders.exceptions;

import al.sankevich.placeholders.dtos.ExceptionInfo;

import static al.sankevich.placeholders.utils.ExceptionUtils.formatNotReservedCharacterEscapedExceptionMessage;

public class NotReservedCharacterEscapedException extends PlaceholderParsingException {

    public NotReservedCharacterEscapedException(final ExceptionInfo info) {
        super(info.component(), formatNotReservedCharacterEscapedExceptionMessage(info));
    }

    public static NotReservedCharacterEscapedException of(final ExceptionInfo info) {
        return new NotReservedCharacterEscapedException(info);
    }
}
