package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
