package pl.coderslab.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/validate")
public class ValidationApiController {
    private final Validator validator;

    public ValidationApiController(Validator validator) {
        this.validator = validator;
    }

    @GetMapping("/test")
    public String validate(HttpServletResponse response) {
        Book book = new Book();
        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        response.setCharacterEncoding("UTF-8");
        return violations.stream()
                .map(cv -> cv.getPropertyPath().toString().concat(" : ").concat(cv.getMessage()))
                .collect(Collectors.joining("<br><br>"));
    }

    @GetMapping("/author")
    public String validateAuthor() {
        Author author = new Author();
        author.setPesel("123");
        author.setEmail("ciasteczko");
        Set<ConstraintViolation<Author>> violations = validator.validate(author);

        if (!violations.isEmpty()) {
            return violations.stream()
                    .map(v -> v.getPropertyPath().toString().concat(" : ").concat(v.getMessage()))
                    .collect(Collectors.joining("<br><br>"));
        }
        return "Author validation complete";
    }

    @GetMapping("/publisher")
    public String validatePublisher() {
        Publisher publisher = new Publisher();
        publisher.setNip("222");
        publisher.setRegon("tak");

        Set<ConstraintViolation<Publisher>> violations = validator.validate(publisher);

        if (!violations.isEmpty()) {
            return violations.stream()
                    .map(v -> v.getPropertyPath().toString().concat(" : ").concat(v.getMessage()))
                    .collect(Collectors.joining("<br><br>"));
        }
        return "Publisher validation complete";
    }
}
