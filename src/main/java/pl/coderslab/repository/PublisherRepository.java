package pl.coderslab.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    Publisher findFirstByNip(String nip);
    Publisher findFirstByRegon(String regon);

    @Modifying
    @Transactional
    @Query("update Publisher p set p.name = ?1, p.nip = ?2, p.regon = ?3 where p.id = ?4")
    void edit(String name, String nip, String regon, Long id);
}
