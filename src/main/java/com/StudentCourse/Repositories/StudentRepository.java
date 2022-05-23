package com.StudentCourse.Repositories;

import com.StudentCourse.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Student findByEmail(String email);
}
