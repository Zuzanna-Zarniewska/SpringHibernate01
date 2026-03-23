package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.app.AuthorDao;
import pl.coderslab.app.BookDao;
import pl.coderslab.repository.BookRepository;
import pl.coderslab.app.PublisherDao;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;
import pl.coderslab.repository.CategoryRepository;
import pl.coderslab.repository.PublisherRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final PublisherRepository publisherRepository;

    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao, BookRepository bookRepository, CategoryRepository categoryRepository, PublisherRepository publisherRepository) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.publisherRepository = publisherRepository;
    }

    @GetMapping("/repo/by-cat/{id}")
    @ResponseBody
    public String bookByCatId(@PathVariable("id") Long id) {
        return bookRepository.findAllByCategoryId(id)
                .stream().map(Book::getTitle)
                .collect(Collectors.joining("<br><br>"));
    }

    @GetMapping("/repo/by-cat")
    @ResponseBody
    public String bookByCat() {
        // dla kategorii o ID = 1
        return bookRepository.findAllByCategory(categoryRepository.findById(1L).get())
                .stream().map(Book::getTitle)
                .collect(Collectors.joining("<br><br>"));
    }

    @GetMapping("/repo/by-author/{id}")
    @ResponseBody
    public String booksByAuthor(@PathVariable("id") Long id) {
        Author author = authorDao.getById(id);
        return bookRepository.findAllByAuthorsContains(author).stream()
                .map(Book::toString)
                .collect(Collectors.joining("<br><br>"));
    }

    @GetMapping("/repo/by-publisher/{id}")
    @ResponseBody
    public String booksByPublisher(@PathVariable("id") Long id) {
        Publisher publisher = publisherDao.getById(id);
        return bookRepository.findAllByPublisher(publisher).stream()
                .map(Book::toString)
                .collect(Collectors.joining("<br><br>"));
    }

    @GetMapping("/repo/by-rating/{rating}")
    @ResponseBody
    public String booksByPublisher(@PathVariable("rating") int rating) {
        return bookRepository.findAllByRating(rating).stream()
                .map(Book::toString)
                .collect(Collectors.joining("<br><br>"));
    }

    @GetMapping("/repo/by-cat/first-ordered-by-title")
    @ResponseBody
    public String firstBookByCatOrderedByTitle() {
        // dla kategorii o ID = 1
        return bookRepository.findFirstByCategoryOrderByTitle(categoryRepository.findById(1L).get()).toString();
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
        book.setAuthors(new ArrayList<>(Arrays.asList(author1, author2)));
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

    @GetMapping("/books-with-publisher")
    @ResponseBody
    public String findBooksWithPublisher() {
        return bookDao.findBooksWithPublisher().stream()
                .map(Book::toString)
                .collect(Collectors.joining("<br><br>"));
    }

    @GetMapping("/find-by-publisher/{publisherName}")
    @ResponseBody
    public String findBooksByPublisher(@PathVariable("publisherName") String publisherName) {
        return bookDao.findBooksByPublisher(publisherName)
                .stream().map(Book::toString)
                .collect(Collectors.joining("<br><br>"));
    }

    @GetMapping("/find-by-author/{id}")
    @ResponseBody
    public String findBooksByAuthor(@PathVariable("id") Long id) {
        Author author = authorDao.getById(id);
        return bookDao.findBooksByAuthor(author).stream()
                .map(Book::toString)
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
