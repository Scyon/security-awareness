package nl.scyon.securecoding.validation;

import org.springframework.stereotype.Service;

@Service
public class NameValidator {

    boolean isValid(String input) {
        // This is a blacklist validation
        // TODO: How can we convert this to whitelist validation?
        return !input.contains("<script");
    }
}
