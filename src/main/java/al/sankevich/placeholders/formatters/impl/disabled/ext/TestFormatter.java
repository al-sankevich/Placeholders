package al.sankevich.placeholders.formatters.impl.disabled.ext;

import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.engines.string.StringEngine;
import al.sankevich.placeholders.formatters.impl.disabled.DisabledPlaceholderValueFormatter;
import lombok.SneakyThrows;

public class TestFormatter extends DisabledPlaceholderValueFormatter<Placeholder> {

    public TestFormatter() {
        super("test");
    }

    @SneakyThrows
    @Override
    public void format(Placeholder placeholder, String formatValue, StringEngine buffer) {
        buffer.append(formatValue);
    }
}
