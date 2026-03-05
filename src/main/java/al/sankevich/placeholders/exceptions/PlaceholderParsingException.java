package al.sankevich.placeholders.exceptions;

import al.sankevich.placeholders.dtos.Component;
import lombok.Getter;

@Getter
public abstract class PlaceholderParsingException extends RuntimeException {

    private final Component component;

    public PlaceholderParsingException(final Component component, final String message) {
        super(message);
        this.component = component;
    }
}
