package com.StudentCourse.services;

import com.StudentCourse.CustomExceptions.AlreadyFoundException;
import com.StudentCourse.CustomExceptions.NotFoundException;
import com.StudentCourse.Repositories.CourseRepo;
import com.StudentCourse.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    private CourseRepo courseRepo;
    public Course addCourse(Course course)
    {
        Course courseX=courseRepo.findByName(course.getName());
        if (courseX != null)
        {
            throw new AlreadyFoundException("Course already exist with same name\nenter a unique name");
        }
        Course course1 = new Course();
        course1.setName(course.getName());
        course1.setAmount(course.getAmount());
        course1.setDescription(course.getDescription());
        course1.setDuration(course.getDuration());
        courseRepo.save(course1);
        return course1;
    }
    public void deleteCourse(String name){
        Course course = courseRepo.findByName(name);
        if (course == null){
            throw new NotFoundException("Course with this name cannot be found");
        }
        courseRepo.delete(course);
    }
}
