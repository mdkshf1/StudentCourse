package com.StudentCourse.controllers;

import com.StudentCourse.CustomExceptions.AlreadyFoundException;
import com.StudentCourse.CustomExceptions.NotFoundException;
import com.StudentCourse.DTO.PassportTO;
import com.StudentCourse.DTO.ReviewTO;
import com.StudentCourse.DTO.StudentTO;
import com.StudentCourse.DTO.SubscribeTO;
import com.StudentCourse.Repositories.PassportRepo;
import com.StudentCourse.entities.Course;
import com.StudentCourse.entities.Passport;
import com.StudentCourse.entities.Review;
import com.StudentCourse.entities.Student;
import com.StudentCourse.services.CourseService;
import com.StudentCourse.services.PassportService;
import com.StudentCourse.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private PassportService passportService;
    @Autowired
    private CourseService courseService;

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@Valid @RequestBody StudentTO student, BindingResult result)
    {
        if (result.hasErrors())
        {
            String error = result.getAllErrors().stream().map(objectError -> {return objectError.getDefaultMessage();}).collect(Collectors.joining("\n"));
            log.warn("Validation failed: "+error);
            return new ResponseEntity<String>(result.getAllErrors().stream().map(objectError -> {return objectError.getDefaultMessage();}).collect(Collectors.joining("\n")), HttpStatus.EXPECTATION_FAILED);
        }
        try{
            Student student1 = studentService.addStudent(student);
            return new ResponseEntity<Student>(student1,HttpStatus.CREATED);
        }catch (AlreadyFoundException e)
        {
            log.warn("Student with this email already found");
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        try {
            Student student = studentService.createStudent();
            return new ResponseEntity<Student>(student,HttpStatus.OK);
        } catch (AlreadyFoundException e) {
            log.warn("Student with this email already found");
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/addPassport")
    public ResponseEntity<?> addPassport(@Valid @RequestBody PassportTO passport, BindingResult result) {
        if (result.hasErrors()) {
            String error = result.getAllErrors().stream().map(objectError -> {
                return objectError.getDefaultMessage();
            }).collect(Collectors.joining("\n"));
            log.warn("Validation failed: " + error);
            return new ResponseEntity<String>(result.getAllErrors().stream().map(objectError -> {
                return objectError.getDefaultMessage();
            }).collect(Collectors.joining("\n")), HttpStatus.EXPECTATION_FAILED);
        }
        try {
            Passport passport1 = passportService.addPassport(passport);
            return new ResponseEntity<Passport>(passport1, HttpStatus.CREATED);
        } catch (NotFoundException e){
            log.warn(e.getMessage());
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (AlreadyFoundException e){
            log.warn(e.getMessage());
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/addCourse")
    public ResponseEntity<?> addCourse(@Valid @RequestBody Course course,BindingResult result){
        if (result.hasErrors()) {
            String error = result.getAllErrors().stream().map(objectError -> {
                return objectError.getDefaultMessage();
            }).collect(Collectors.joining("\n"));
            log.warn("Validation failed: " + error);
            return new ResponseEntity<String>(result.getAllErrors().stream().map(objectError -> {
                return objectError.getDefaultMessage();
            }).collect(Collectors.joining("\n")), HttpStatus.EXPECTATION_FAILED);
        }
        try{
            return new ResponseEntity<Course>(courseService.addCourse(course),HttpStatus.OK);
        }catch (AlreadyFoundException e)
        {
            log.warn(e.getMessage());
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/subscribeCourse")
    public ResponseEntity<?> subscribeCourse(@RequestBody SubscribeTO subscribe){
        try{
            studentService.subscribeCourse(subscribe);
            return new ResponseEntity<String>("Thanks for Subscribing our course",HttpStatus.CREATED);
        }catch (NotFoundException e)
        {
            log.warn(e.getMessage());
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (AlreadyFoundException e)
        {
            log.warn(e.getMessage());
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/addReview")
    public ResponseEntity<?> addReview(@Valid @RequestBody ReviewTO review,BindingResult result){
        if (result.hasErrors()) {
            String error = result.getAllErrors().stream().map(objectError -> {
                return objectError.getDefaultMessage();
            }).collect(Collectors.joining("\n"));
            log.warn("Validation failed: " + error);
            return new ResponseEntity<String>(result.getAllErrors().stream().map(objectError -> {
                return objectError.getDefaultMessage();
            }).collect(Collectors.joining("\n")), HttpStatus.EXPECTATION_FAILED);
        }
        try{
            return new ResponseEntity<ReviewTO>(studentService.addReview(review),HttpStatus.CREATED);
        }catch (NotFoundException e){
            log.warn(e.getMessage());
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/student/{email}")
    public ResponseEntity<?> deleteStudent(@PathVariable("email")String email){
        try{
            studentService.deleteStudent(email);
            return new ResponseEntity<String>("User with "+email+"is deleted",HttpStatus.OK);
        }catch (NotFoundException e){
            log.warn(e.getMessage());
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/course/{name}")
    public ResponseEntity<?> deleteCourse(@PathVariable("name")String name){
        try{
            courseService.deleteCourse(name);
            return new ResponseEntity<String>("Course with "+name+"is deleted",HttpStatus.OK);
        }catch (NotFoundException e){
            log.warn(e.getMessage());
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    /*@DeleteMapping("/passport/")*/
}
