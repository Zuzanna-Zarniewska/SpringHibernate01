package pl.coderslab.controller;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Author;
import pl.coderslab.repository.AuthorRepository;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorApiController {
    private final AuthorRepository authorRepository;

    public AuthorApiController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/")
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Author findById(@PathVariable("id") Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Author add(@RequestBody Author author) {
        authorRepository.save(author);
        return author;
    }

    @PutMapping("/{id}")
    public Author update(@PathVariable("id") Long id, @RequestBody Author author) {
        authorRepository.edit(author.getFirstName(), author.getLastName(), author.getEmail(), author.getPesel(), id);
        return author;
    }
    // ^ mozna tez nie tworzyc dodatkowej metody edit() w repo, tylko pobrac we wnetrzu obslugi puta (tutaj, powyzej)
    // obiekt po ID, a nastepnie pozmieniac jego parametry i na koniec zapisac

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        authorRepository.deleteById(id);
    }
}
