package pl.coderslab.app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Publisher;

@Repository
@Transactional
public class PublisherDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Publisher publisher) {
        entityManager.persist(publisher);
    }

    public void edit(Publisher publisher) {
        entityManager.merge(publisher);
    }

    public Publisher getById(long id) {
        return entityManager.find(Publisher.class, id);
    }

    public void deleteById(long id) {
        Publisher toDelete = getById(id);
        entityManager.remove(entityManager.contains(toDelete) ? toDelete : entityManager.merge(toDelete));
    }
}
