package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findFirstByEmail(String email);
    Author findFirstByPesel(String pesel);
    List<Author> findAllByLastName(String lastName);

    @Query("select a from Author a where a.email like ?1%")
    List<Author> findAllByEmailBeginingQuery(String emailBegining);

    @Query("select a from Author a where a.pesel like ?1%")
    List<Author> findAllByPeselBeginingQuery(String peselBegining);
}
