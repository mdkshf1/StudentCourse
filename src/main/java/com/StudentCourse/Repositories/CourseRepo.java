package com.StudentCourse.Repositories;

import com.StudentCourse.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course,Long> {
    Course findByName(String name);
}
