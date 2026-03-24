package pl.coderslab.services;

import org.springframework.stereotype.Service;
import pl.coderslab.dto.StudentDTO;
import pl.coderslab.entity.Student;
import pl.coderslab.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}
