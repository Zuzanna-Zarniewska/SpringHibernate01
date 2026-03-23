package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.app.PersonDao;
import pl.coderslab.app.PersonDetailsDao;
import pl.coderslab.entity.Person;
import pl.coderslab.entity.PersonDetails;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonDao personDao;
    private final PersonDetailsDao personDetailsDao;

    public PersonController(PersonDao personDao, PersonDetailsDao personDetailsDao) {
        this.personDao = personDao;
        this.personDetailsDao = personDetailsDao;
    }

    @GetMapping("/save")
    @ResponseBody
    public String savePerson() {
        PersonDetails personDetails = new PersonDetails();
        personDetails.setFirstName("First Name");
        personDetails.setLastName("Last Name");
        personDetails.setCity("Sample City");
        personDetails.setStreet("Sample Street");
        personDetails.setStreetNumber(1);
        personDetailsDao.save(personDetails);

        Person person = new Person();
        person.setLogin("sampleLogin");
        person.setEmail("samplemail@mail.com");
        person.setPassword("pass");
        person.setPersonDetails(personDetails);
        personDao.save(person);

        return "Saved the person with ID: " + person.getId();
    }

    @GetMapping("/edit/{id}")
    @ResponseBody
    public String editPerson(@PathVariable("id") Long id) {
        Person person = personDao.getById(id);
        person.setLogin("Edited Login");
        person.setEmail("edited@mail.com");
        person.setPassword("edited pass");
        personDao.edit(person);

        return "Edited the person with id: " + person.getId();
    }

    @GetMapping("/find/{id}")
    @ResponseBody
    public String findPerson(@PathVariable("id") Long id) {
        Person person = personDao.getById(id);
        return person.toString();
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public String deletePerson(@PathVariable("id") Long id) {
        personDao.deleteById(id);
        return "Deleted the person with id: " + id;
    }
}
