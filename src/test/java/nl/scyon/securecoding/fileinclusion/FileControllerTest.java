package nl.scyon.securecoding.fileinclusion;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import nl.scyon.securecoding.authentication.SpringSecurityConfiguration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest({FileController.class, SpringSecurityConfiguration.class})
class FileControllerTest {

    @Autowired
    private MockMvc client;

    @Test
    void testExistingFileReturnsCorrectContent() throws Exception {
        client.perform(get("/files?fileName=file1.txt"))
            .andExpect(status().isOk())
            .andExpect(content().string("This is file 1.\n"));
    }

    @Test
    void testNonExistingFileReturns404() throws Exception {
        client.perform(get("/files?fileName=nonexisting.txt"))
            .andExpect(status().isNotFound());
    }
}
