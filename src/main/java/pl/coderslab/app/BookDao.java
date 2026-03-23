package pl.coderslab.app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;

import java.util.List;

@Repository
@Transactional
public class BookDao {
    @PersistenceContext
    private EntityManager entityManager;

    public BookDao() {

    }

    public void save(Book book) {
        entityManager.persist(book);
    }

    public void edit(Book book) {
        entityManager.merge(book);
    }

    public Book getById(long id) {
        return entityManager.find(Book.class, id);
    }

    public void deleteById(long id) {
        Book toDelete = getById(id);
        entityManager.remove(entityManager.contains(toDelete) ? toDelete : entityManager.merge(toDelete));
    }

    public List<Book> findAll() {
        return entityManager.createQuery("select b from Book b", Book.class)
                .getResultList();
    }

    public List<Book> findAllByRating(int rating) {
        return entityManager.createQuery("select b from Book b where b.rating =: rating", Book.class)
                .setParameter("rating", rating)
                .getResultList();
    }

    public List<Book> findBooksWithPublisher() {
        return entityManager.createQuery("select b from Book b where b.publisher is not null", Book.class)
                .getResultList();
    }

    public List<Book> findBooksByPublisher(String publisherName) {
        return entityManager.createQuery("select b from Book b where b.publisher.name =: publisherName")
                .setParameter("publisherName", publisherName)
                .getResultList();
    }
}
