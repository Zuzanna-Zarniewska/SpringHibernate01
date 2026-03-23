package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Category;
import pl.coderslab.entity.Publisher;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByRating(int rating);
    List<Book> findAllByTitle(String title);
    List<Book> findAllByCategory(Category category);
    List<Book> findAllByCategoryId(Long id);

    List<Book> findAllByAuthorsId(Long id);

    List<Book> findAllByAuthorsContains(Author author);

    List<Book> findAllByPublisher(Publisher publisher);

    Book findFirstByCategoryOrderByTitle(Category category);
}
