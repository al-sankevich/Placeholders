package al.sankevich.placeholders.exceptions;

import al.sankevich.placeholders.dtos.ExceptionInfo;

import static al.sankevich.placeholders.utils.ExceptionUtils.formatEmptyComponentExceptionMessage;

public class EmptyComponentException extends PlaceholderParsingException {

    public EmptyComponentException(final ExceptionInfo info) {
        super(info.component(), formatEmptyComponentExceptionMessage(info));
    }

    public static EmptyComponentException of(final ExceptionInfo info) {
        return new EmptyComponentException(info);
    }
}
