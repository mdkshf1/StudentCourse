package com.StudentCourse.Repositories;

import com.StudentCourse.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
