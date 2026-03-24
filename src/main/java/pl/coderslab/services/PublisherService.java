package pl.coderslab.services;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.Publisher;
import pl.coderslab.repository.PublisherRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    public Optional<Publisher> findById(Long id) {
        return publisherRepository.findById(id);
    }

    public void save(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    public void edit(String name, String nip, String regon, Long id) {
        publisherRepository.edit(name, nip, regon, id);
    }

    public void deleteById(Long id) {
        publisherRepository.deleteById(id);
    }
}
