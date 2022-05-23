package com.StudentCourse.Repositories;

import com.StudentCourse.entities.Course;
import com.StudentCourse.entities.Review;
import com.StudentCourse.entities.ReviewPK;
import com.StudentCourse.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, ReviewPK> {
    Review findReviewByStudentAndCourse(Student student, Course course);
}
