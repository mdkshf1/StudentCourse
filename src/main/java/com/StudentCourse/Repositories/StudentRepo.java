package com.StudentCourse.Repositories;

import com.StudentCourse.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Long> {
    /*Student findStudentByEmail(String email);*/
    Student findByEmail(String email);
}
