package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.app.AuthorDao;
import pl.coderslab.app.BookDao;
import pl.coderslab.app.PublisherDao;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;

    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }

    @GetMapping("/add-with-publisher")
    @ResponseBody
    public String addWithPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("somePublisher");
        publisherDao.save(publisher);

        Book book = new Book();
        book.setTitle("NewBook");
        book.setDescription("NewDescription");
        book.setRating(10);
        book.setPublisher(publisher);
        bookDao.save(book);
        return "Added the book with ID: " + book.getId();
    }

    @GetMapping("/add-with-authors")
    @ResponseBody
    public String addWithAuthors() {
        Author author1 = new Author();
        author1.setFirstName("Pierwszy");
        author1.setLastName("Autor");
        authorDao.save(author1);

        Author author2 = new Author();
        author2.setFirstName("Drugi");
        author2.setLastName("Autor");
        authorDao.save(author2);

        Publisher publisher = new Publisher();
        publisher.setName("Wydawnictwo");
        publisherDao.save(publisher);

        Book book = new Book();
        book.setPublisher(publisher);
        book.setTitle("Ksiazka Dwoch Autorow");
        book.setRating(5);
        book.setDescription("Opis");
        book.setAuthors(new HashSet<>(Arrays.asList(author1, author2)));
        bookDao.save(book);

        return "Added the book with ID: " + book.getId();
    }

    @GetMapping("/find-by-rating/{rating}")
    @ResponseBody
    public String findBookByRating(@PathVariable("rating") Integer rating) {
        return bookDao.findAllByRating(rating)
                .stream().map(Book::toString)
                .collect(Collectors.joining("<br><br>"));
    }

    @GetMapping("/save/{title}/{rating}/{description}")
    @ResponseBody
    public String saveBook(@PathVariable("title") String title, @PathVariable("rating") int rating,
                           @PathVariable("description") String description) {
        Book book = new Book();
        book.setTitle(title);
        book.setRating(rating);
        book.setDescription(description);
        bookDao.save(book);

        return "Saved the book with id: " + book.getId();
    }

    @GetMapping("/edit/{id}/{title}/{rating}/{description}")
    @ResponseBody
    public String editBook(@PathVariable("id") Long id, @PathVariable("title") String title, @PathVariable("rating") int rating,
                           @PathVariable("description") String description) {
        Book book = bookDao.getById(id);
        book.setTitle(title);
        book.setRating(rating);
        book.setDescription(description);
        bookDao.edit(book);

        return "Edited the book with id: " + book.getId();
    }

    @GetMapping("/find/{id}")
    @ResponseBody
    public String findBook(@PathVariable("id") Long id) {
        Book book = bookDao.getById(id);
        return book.toString();
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable("id") Long id) {
        bookDao.deleteById(id);
        return "Deleted the book with id: " + id;
    }
}
