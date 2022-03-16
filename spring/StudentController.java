package com.example.miniproject.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.miniproject.model.Student;
import com.example.miniproject.service.StudentService;

@CrossOrigin(origins = "Http://localhost:8081")
@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("students/id/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") long id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("students/course/{course}")
    public ResponseEntity<List<Student>> getStudentsByCourse(@PathVariable("course") String course) {
        return studentService.findByCourse(course);
    }

    @GetMapping("students/section/{section}")
    public ResponseEntity<List<Student>> getStudentsBySection(@PathVariable("section") int section) {
        return studentService.findBySection(section);
    }

    @PostMapping("students/add-student")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("students/update-student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("students/remove-student/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") long id) {
        return studentService.deleteStudent(id);
    }

    @DeleteMapping("students/remove-student/all")
    public ResponseEntity<HttpStatus> deleteAllStudents() {
        return studentService.deleteAllStudents();
    }
}