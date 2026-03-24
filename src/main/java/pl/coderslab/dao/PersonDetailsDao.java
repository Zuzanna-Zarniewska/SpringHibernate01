package pl.coderslab.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.PersonDetails;

@Repository
@Transactional
public class PersonDetailsDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(PersonDetails personDetails) {
        entityManager.persist(personDetails);
    }

    public void edit(PersonDetails personDetails) {
        entityManager.merge(personDetails);
    }

    public PersonDetails getById(long id) {
        return entityManager.find(PersonDetails.class, id);
    }

    public void deleteById(long id) {
        PersonDetails toDelete = getById(id);
        entityManager.remove(entityManager.contains(toDelete) ? toDelete : entityManager.merge(toDelete));
    }
}
