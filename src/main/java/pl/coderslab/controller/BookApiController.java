package pl.coderslab.controller;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Book;
import pl.coderslab.repository.BookRepository;
import pl.coderslab.services.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookApiController {
    private final BookService bookService;

    public BookApiController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public List<Book> getAll() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable("id") Long id) {
        return bookService.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Book add(@RequestBody Book book) {
        bookService.save(book);
        return book;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        bookService.deleteById(id);
    }
}
