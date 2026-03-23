package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.app.AuthorDao;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;

@Controller
public class AuthorController {
    private AuthorDao dao;

    public AuthorController(AuthorDao dao) {
        this.dao = dao;
    }

    @GetMapping("/author/save/{firstName}/{lastName}")
    @ResponseBody
    public String saveAuthor(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        dao.save(author);

        return "Saved the author with id: " + author.getId();
    }

    @GetMapping("/author/edit/{id}/{firstName}/{lastName}")
    @ResponseBody
    public String editAuthor(@PathVariable("id") Long id, @PathVariable("firstName") String firstName,
                           @PathVariable("lastName") String lastName) {
        Author author = dao.getById(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);

        return "Edited the author with id: " + author.getId();
    }

    @GetMapping("/author/find/{id}")
    @ResponseBody
    public String findAuthor(@PathVariable("id") Long id) {
        Author author = dao.getById(id);
        return author.toString();
    }

    @GetMapping("/author/delete/{id}")
    @ResponseBody
    public String deleteAuthor(@PathVariable("id") Long id) {
        dao.deleteById(id);
        return "Deleted the author with id: " + id;
    }
}
