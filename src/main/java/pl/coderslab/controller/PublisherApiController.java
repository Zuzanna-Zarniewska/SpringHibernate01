package pl.coderslab.controller;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Publisher;
import pl.coderslab.repository.PublisherRepository;
import pl.coderslab.services.PublisherService;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
public class PublisherApiController {
    private final PublisherService publisherService;

    public PublisherApiController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/")
    public List<Publisher> findAll() {
        return publisherService.findAll();
    }

    @GetMapping("/{id}")
    public Publisher findById(@PathVariable("id") Long id) {
        return publisherService.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Publisher add(@RequestBody Publisher publisher) {
        publisherService.save(publisher);
        return publisher;
    }

    @PutMapping("/{id}")
    public Publisher update(@PathVariable("id") Long id, @RequestBody Publisher publisher) {
        publisherService.edit(publisher.getName(), publisher.getNip(), publisher.getRegon(), id);
        return publisher;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        publisherService.deleteById(id);
    }
}
