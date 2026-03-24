package pl.coderslab.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.dto.StudentDTO;
import pl.coderslab.entity.Student;
import pl.coderslab.repository.StudentRepository;
import pl.coderslab.services.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.findAll().stream()
                .map(student -> {
                    StudentDTO studentDTO = new StudentDTO();
                    studentDTO.setId(student.getId());
                    studentDTO.setFirstName(student.getFirstName());
                    studentDTO.setLastName(student.getLastName());
                    studentDTO.setIndexNumber(student.getIndexNumber());
                    studentDTO.setAverageGrade(student.getAverageGrade());
                    return studentDTO;
                }).toList();
    }
}
