package nl.scyon.securecoding.validation;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class NameValidator {

    private static final Pattern NAME_REGEX = Pattern.compile("^[\\p{Alnum} ]+$");

    boolean isValid(String input) {
        return NAME_REGEX.matcher(input).matches();
    }
}
