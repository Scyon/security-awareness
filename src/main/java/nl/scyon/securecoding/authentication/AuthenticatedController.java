package nl.scyon.securecoding.authentication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticated")
public class AuthenticatedController {

    @GetMapping("/ping")
    public String getPing() {
        return "Hi authenticated Ping!";
    }

    @GetMapping("/pong")
    public String getPong() {
        return "Hi authenticated Pong!";
    }

    /**
     * Protected by {@link ApiKeyFilter}.
     */
    @GetMapping("/apiKey")
    public String getPageUsingApiKey() {
        return "Authorized using API key";
    }
}
