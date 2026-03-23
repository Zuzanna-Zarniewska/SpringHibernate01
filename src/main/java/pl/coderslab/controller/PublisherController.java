package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.app.PublisherDao;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;

@Controller
@RequestMapping("/publisher")
public class PublisherController {
    private PublisherDao dao;

    public PublisherController(PublisherDao dao) {
        this.dao = dao;
    }

    @GetMapping("/save/{name}")
    @ResponseBody
    public String savePublisher(@PathVariable("name") String name) {
        Publisher publisher = new Publisher();
        publisher.setName(name);
        dao.save(publisher);

        return "Saved the publisher with ID: " + publisher.getId();
    }

    @GetMapping("/edit/{id}/{name}")
    @ResponseBody
    public String editPublisher(@PathVariable("id") Long id, @PathVariable("name") String name) {
        Publisher publisher = dao.getById(id);
        publisher.setName(name);
        dao.edit(publisher);

        return "Edited the publisher with id: " + publisher.getId();
    }

    @GetMapping("/find/{id}")
    @ResponseBody
    public String findPublisher(@PathVariable("id") Long id) {
        Publisher publisher = dao.getById(id);
        return publisher.toString();
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public String deletePublisher(@PathVariable("id") Long id) {
        dao.deleteById(id);
        return "Deleted the publisher with id: " + id;
    }
}
