package al.sankevich.utils;

import al.sankevich.placeholders.dtos.Placeholder;
import al.sankevich.placeholders.dtos.WrappablePlaceholder;
import org.junit.jupiter.api.Assertions;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class AssertUtils {

    public static void assertEquals(Placeholder expected, Placeholder actual) {
        Assertions.assertEquals(expected.getName(), actual.getName());
        Assertions.assertEquals(expected.getPosition(), actual.getPosition());
        assertThat(actual.getEnabledFormats()).containsExactlyInAnyOrderElementsOf(expected.getEnabledFormats());
        assertThat(actual.getDisabledFormats()).containsExactlyInAnyOrderElementsOf(expected.getDisabledFormats());
    }

    public static void assertEquals(WrappablePlaceholder expected, WrappablePlaceholder actual) {
        Assertions.assertEquals(expected.getName(), actual.getName());
        Assertions.assertEquals(expected.getPosition(), actual.getPosition());
        assertThat(actual.getEnabledFormats()).containsExactlyInAnyOrderElementsOf(expected.getEnabledFormats());
        assertThat(actual.getDisabledFormats()).containsExactlyInAnyOrderElementsOf(expected.getDisabledFormats());
        Assertions.assertEquals(expected.getWrapper(), actual.getWrapper());
    }
}
