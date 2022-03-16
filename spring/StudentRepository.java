package com.example.miniproject.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.miniproject.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
    List<Student> findByCourse(String course);

    List<Student> findBySection(int section);
}
