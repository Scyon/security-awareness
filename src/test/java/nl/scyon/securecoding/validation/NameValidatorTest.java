package nl.scyon.securecoding.validation;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class NameValidatorTest {

    private final NameValidator subject = new NameValidator();

    @Test
    void testIsValid_noHtml() {
        assertThat(subject.isValid("Scyon")).isTrue();
    }

    @Test
    void testIsValid_html() {
        assertThat(subject.isValid("<script>alert('PWND!');</script>")).isFalse();
    }
}
