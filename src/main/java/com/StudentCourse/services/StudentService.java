package com.StudentCourse.services;

import com.StudentCourse.CustomExceptions.AlreadyFoundException;
import com.StudentCourse.CustomExceptions.NotFoundException;
import com.StudentCourse.DTO.ReviewTO;
import com.StudentCourse.DTO.StudentTO;
import com.StudentCourse.DTO.SubscribeTO;
import com.StudentCourse.Repositories.CourseRepo;
import com.StudentCourse.Repositories.ReviewRepo;
import com.StudentCourse.Repositories.StudentRepo;
import com.StudentCourse.entities.Course;
import com.StudentCourse.entities.Review;
import com.StudentCourse.entities.Student;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private ReviewRepo reviewRepo;
    public Student addStudent(StudentTO student)
    {
        Student studentX = studentRepo.findByEmail(student.getEmail());
        if (studentX !=null)
        {
            throw new AlreadyFoundException("Student with this email is already already present");
        }
        Student student1 = new Student();
        student1.setEmail(student.getEmail());
        student1.setName(student.getName());
        student1.setPassword(student.getPassword());
        studentRepo.save(student1);
        return student1;
    }
    public Student createStudent()
    {
        Student studentX = studentRepo.findByEmail("abc@gmail.com");
        if (studentX !=null)
        {
            throw new AlreadyFoundException("Student with this email is already already present");
        }
        Student student = new Student();
        student.setName("Kashif");
        student.setEmail("abc@gmail.com");
        student.setPassword("123456");
        studentRepo.save(student);
        return student;
    }

    public void subscribeCourse(SubscribeTO subscribe){
        Course course = courseRepo.findByName(subscribe.getCourseName());
        if (course == null){
            throw new NotFoundException("Cannot find Course with this name");
        }
        System.out.println(course.getName());
        Student student = studentRepo.findByEmail(subscribe.getEmail());
        if (student == null){
            throw new NotFoundException("Cannot find student with this email");
        }
        List<Course> courses = student.getCourses();
        for (Course courseX:courses
             ) {
            if (courseX == course){
                throw new AlreadyFoundException("You already Subscribed this course");
            }
        }
        courses.add(course);
        student.setCourses(courses);
        List<Student> students = course.getStudent();
        students.add(student);
        course.setStudent(students);
        studentRepo.save(student);
        courseRepo.save(course);
    }
    public ReviewTO addReview(ReviewTO review){
        System.out.println(review.getEmail());
        System.out.println(review.getCourseName());
        Course course = courseRepo.findByName(review.getCourseName());
        if (course == null){
            throw new NotFoundException("Cannot find Course with this name");
        }
        Student student = studentRepo.findByEmail(review.getEmail());
        if (student == null)
        {
            throw new NotFoundException("Cannot find Student with this email");
        }
        System.out.println(student.getEmail());
        Review review1 = new Review();
        review1.setStudent(student);
        review1.setCourse(course);
        review1.setComment(review.getComment());
        review1.setRating(review.getRating());
        List<Review> reviews = course.getReviews();
        reviews.add(review1);
        course.setReviews(reviews);
        List<Review> reviewList = student.getReview();
        reviewList.add(review1);
        student.setReview(reviewList);
        reviewRepo.save(review1);
        return review;
    }

    public void deleteStudent(String email){
        Student student = studentRepo.findByEmail(email);
        if (student == null){
            throw new NotFoundException("Student with this email id is not found");
        }
        studentRepo.delete(student);
    }
}
