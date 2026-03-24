package pl.coderslab.controller;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Publisher;
import pl.coderslab.repository.PublisherRepository;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
public class PublisherApiController {
    private final PublisherRepository publisherRepository;

    public PublisherApiController(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @GetMapping("/")
    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    @GetMapping("/{id}")
    public Publisher findById(@PathVariable("id") Long id) {
        return publisherRepository.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Publisher add(@RequestBody Publisher publisher) {
        publisherRepository.save(publisher);
        return publisher;
    }

    @PutMapping("/{id}")
    public Publisher update(@PathVariable("id") Long id, @RequestBody Publisher publisher) {
        publisherRepository.edit(publisher.getName(), publisher.getNip(), publisher.getRegon(), id);
        return publisher;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        publisherRepository.deleteById(id);
    }
}
