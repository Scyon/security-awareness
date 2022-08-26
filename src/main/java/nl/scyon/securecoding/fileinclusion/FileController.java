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
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/files")
public class FileController {

    private static final String PATH_TO_FILES = "/files";

    @GetMapping
    public ClassPathResource getFile(@RequestParam String fileName) {

        Path file = Paths.get(PATH_TO_FILES, fileName);
        Path normalizedPath = file.normalize();
        if (normalizedPath.startsWith(PATH_TO_FILES)) {
            log.info("Serving classpath file {} to client.", file);
            return new ClassPathResource(file.toString());
        } else {
            log.error("SECURITY ALERT: Path Traversal attack prevented using path {}", fileName);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nothing to see here!");
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    String handleFileNotFound(FileNotFoundException e) {
        return "O-oh, not found!";
    }
}
