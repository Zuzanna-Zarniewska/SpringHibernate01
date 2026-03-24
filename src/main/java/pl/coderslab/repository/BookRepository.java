package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @Query("select b from Book b where b.title =?1")
    List<Book> findAllByTitleByQuery(String title);

    @Query("select b from Book b where b.category =?1")
    List<Book> findAllByCategoryQuery(Category category);

    @Query("select b from Book b where b.rating between ?1 and ?2")
    List<Book> findAllByRatingBetweenQuery(int start, int stop);

    @Query("select b from Book b where b.publisher = ?1")
    List<Book> findBooksByPublisherQuery(Publisher publisher);

    @Query("select b from Book b where b.category = ?1 order by b.title limit 1")
    Book findFirstByCategoryOrderedByTitleQuery(Category category);
}
