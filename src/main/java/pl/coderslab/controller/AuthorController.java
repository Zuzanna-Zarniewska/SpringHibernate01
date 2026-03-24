package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.app.AuthorDao;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.repository.AuthorRepository;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/author")
public class AuthorController {
    private AuthorDao dao;
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorDao dao, AuthorRepository authorRepository) {
        this.dao = dao;
        this.authorRepository = authorRepository;
    }

    @GetMapping("/test/{begining}")
    @ResponseBody
    public String test(@PathVariable("begining") String begining) {
//        String begining = "mail";
//        return authorRepository.findAllByEmailBeginingQuery(begining).stream()
//                .map(Author::toString)
//                .collect(Collectors.joining("<br><br>"));
//        String begining = "10";
        return authorRepository.findAllByPeselBeginingQuery(begining).stream()
                .map(Author::toString)
                .collect(Collectors.joining("<br><br>"));
    }

    @GetMapping("/by-mail/{email}")
    @ResponseBody
    public String authorByEmail(@PathVariable("email") String email) {
        return authorRepository.findFirstByEmail(email).toString();
    }

    @GetMapping("/by-pesel/{pesel}")
    @ResponseBody
    public String authorByPesel(@PathVariable("pesel") String pesel) {
        return  authorRepository.findFirstByPesel(pesel).toString();
    }

    @GetMapping("/by-lastname/{lastName}")
    @ResponseBody
    public String authorByLastName(@PathVariable("lastName") String lastName) {
        return authorRepository.findAllByLastName(lastName)
                .stream().map(Author::toString)
                .collect(Collectors.joining("<br><br>"));
    }

    @GetMapping("/save/{firstName}/{lastName}")
    @ResponseBody
    public String saveAuthor(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        dao.save(author);

        return "Saved the author with id: " + author.getId();
    }

    @GetMapping("/edit/{id}/{firstName}/{lastName}")
    @ResponseBody
    public String editAuthor(@PathVariable("id") Long id, @PathVariable("firstName") String firstName,
                           @PathVariable("lastName") String lastName) {
        Author author = dao.getById(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);

        return "Edited the author with id: " + author.getId();
    }

    @GetMapping("/find/{id}")
    @ResponseBody
    public String findAuthor(@PathVariable("id") Long id) {
        Author author = dao.getById(id);
        return author.toString();
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public String deleteAuthor(@PathVariable("id") Long id) {
        dao.deleteById(id);
        return "Deleted the author with id: " + id;
    }
}
