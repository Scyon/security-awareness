package nl.scyon.securecoding.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest({AuthenticatedController.class, SpringSecurityConfiguration.class})
class AuthenticatedControllerTest {

    @Autowired
    private MockMvc client;

    @Test
    void testGetPing_unauthenticated() throws Exception {

        client.perform(get("/authenticated/ping"))
            .andExpect(status().isUnauthorized());
    }

    @WithMockUser
    @Test
    void testGetPing_authenticated() throws Exception {

        client.perform(get("/authenticated/ping"))
            .andExpect(status().isOk())
            .andExpect(content().string("Hi authenticated Ping!"));
    }
}
