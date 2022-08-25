package nl.scyon.securecoding.fileinclusion;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/files")
public class FileController {

    private static final String PATH_TO_FILES = "/files";

    @GetMapping
    public ClassPathResource getFile(@RequestParam String fileName) {
        // TODO: how can we make this method secure?

        Path file = Paths.get(PATH_TO_FILES, fileName);
        return new ClassPathResource(file.toString());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    String handleFileNotFound(FileNotFoundException e) {
        return "O-oh, not found!";
    }
}
