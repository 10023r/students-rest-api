package test_task.students_application.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import test_task.students_application.model.Student;
import test_task.students_application.repository.StudentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentRepository repo;

    @Autowired
    StudentController(StudentRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return repo.findAll();
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
        if (repo.deleteById(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("No user with such id", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/students")
    public ResponseEntity<?> createStudent(@Valid Student student) {
        int generatedId = repo.saveStudent(student);
        if (generatedId > 0) {
            return new ResponseEntity<>(generatedId, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Couldn't create such student", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach( error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
