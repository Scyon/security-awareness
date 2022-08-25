package nl.scyon.securecoding.validation;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/html")
@RequiredArgsConstructor
public class PrettyNameController {

    private static final String SCYON_SVG = """
            <svg preserveAspectRatio="xMidYMid meet" data-bbox="0 0.157 803 219.843" viewBox="0 0.157 803 219.843" height="220" width="803" xmlns="http://www.w3.org/2000/svg" shape-rendering="geometricPrecision" text-rendering="geometricPrecision" image-rendering="optimizeQuality" fill-rule="evenodd" clip-rule="evenodd" data-type="color" role="presentation" aria-hidden="true" aria-labelledby="svgcid-dbndon-yij4at"><title id="svgcid-dbndon-yij4at"></title>
                <g>
                    <path d="M155 110c-16-40-78-18-89 25 9-6 17-8 25-8-15 12-30 29-35 53 14 15 31 28 51 40 71-40 109-113 108-176-13 84-52 112-80 107-36-9-16-72 20-41zM0 33c0 31 4 56 13 73 16-18 36-33 59-45-14 2-28 5-42 13-3-15-4-30-4-44C49 16 89 0 131 6 96-8 26 4 0 33z" fill="#0f224a" data-color="1"></path>
                    <path d="M794 164c-2 0-4 0-6-1-1 0-3-1-4-2l-61-57v59h-17V85c0-2 1-4 3-5 1-2 4-2 7-2 2 0 4 0 5 1 1 0 3 1 3 2l61 59V80h18v77c0 2-1 4-3 6-1 1-3 1-6 1zM20 127c5 11 11 20 18 29 9-36 28-61 62-75-38 3-66 16-80 46zm94-87c-4 6-3 12 1 17-19 1-40 8-60 25 67-23 117 2 106 34 35-54-37-56-47-76zm283 123c-7 0-12-1-16-2s-7-3-9-5c-3-2-5-5-6-9-1-3-1-8-1-13v-26c0-5 0-10 1-13 1-4 3-7 6-9 2-2 5-4 9-5s9-1 16-1h57v15h-57c-2 0-5 1-7 1-1 1-3 1-4 2s-2 3-2 5c-1 1-1 3-1 6v24c0 3 0 5 1 7 0 2 1 3 2 4s3 2 4 2c2 1 5 1 8 1h56v16h-57zm-144 0v-16h63c4 0 6-1 8-2 1 0 2-2 2-5v-5c0-2-1-4-2-5-2-1-4-2-7-2h-40c-5 0-8 0-12-1-3-1-5-2-7-4s-3-4-3-7c-1-2-2-5-2-9v-4c0-4 1-8 2-11s2-5 4-7 4-3 7-4 7-1 11-1h64v15h-62c-3 0-5 1-7 2-1 1-2 3-2 7v2c0 3 1 5 2 6s4 1 7 1h38c5 0 9 1 12 2 3 0 6 2 8 3 2 2 4 4 4 7 1 3 2 6 2 9v7c0 4-1 7-2 10 0 3-2 5-4 7-2 1-5 3-8 3-3 1-7 2-12 2h-64zm259 0v-46l44-37h24l-49 44v39h-19zm153-54c0-3 0-5-1-7 0-1-1-3-2-4s-3-2-5-2c-1 0-4-1-6-1h-30c-3 0-6 1-7 1-2 0-4 1-5 2s-1 3-2 4v38c1 2 1 3 2 4s3 2 5 2c1 1 4 1 7 1h30c2 0 5 0 6-1 2 0 4-1 5-2s2-2 2-4c1-2 1-4 1-7v-24zm-45 54c-6 0-11-1-15-2s-8-3-10-5c-3-2-4-5-5-9-1-3-2-8-2-13v-26c0-5 1-10 2-13 1-4 2-7 5-9 2-2 6-4 10-5s9-1 15-1h31c7 0 12 0 16 1s7 3 10 5c2 2 4 5 5 9 1 3 1 8 1 13v26c0 5 0 10-1 13-1 4-3 7-5 9-3 2-6 4-10 5s-9 2-16 2h-31z" fill="#0f224a" data-color="1"></path>
                    <path fill="#0f224a" d="m508 112-38-33h25l26 22-13 11z" data-color="1"></path>
                </g>
            </svg>
            """;
    private final NameValidator nameValidator;

    @GetMapping(value = "/pretty", produces = MediaType.TEXT_HTML_VALUE)
    public String generatePrettyName(@RequestParam(required = false, defaultValue = "John Doe") String name) {
        StringBuilder output = new StringBuilder();
        output.append("<html><body>");
        output.append("<h1>Hi there!</h1>");

        if (!nameValidator.isValid(name)) {
            log.error("SECURITY ALERT: XSS attack prevented! Client tried to generate a pretty name using input: {}", name);
            output.append("<div>Arrr you dirty hacker! Get lost or I'll call the cops on you!</div>");
        } else {
            output
                .append("<h3 style=\"padding: 15px; background: linear-gradient(#0f224a, #4364ab); color: #ffffff\">")
                .append("Beautiful ")
                .append(name)
                .append(".")
                .append("</h3>")

                // Add Scyon logo for the pure fun of it
                .append(SCYON_SVG);
        }

        output.append("</body></html");
        return output.toString();
    }
}
