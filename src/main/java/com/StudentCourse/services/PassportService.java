package com.StudentCourse.services;

import com.StudentCourse.CustomExceptions.AlreadyFoundException;
import com.StudentCourse.CustomExceptions.NotFoundException;
import com.StudentCourse.DTO.PassportTO;
import com.StudentCourse.DTO.StudentTO;
import com.StudentCourse.Repositories.PassportRepo;
import com.StudentCourse.Repositories.StudentRepo;
import com.StudentCourse.entities.Passport;
import com.StudentCourse.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PassportService {
    @Autowired
    private PassportRepo passportRepo;
    @Autowired
    private StudentRepo studentRepo;
    public Passport addPassport(PassportTO passport)
    {
        Passport passportCheck = passportRepo.findByPassportNumber(passport.getPassportNumber());
        if (passportCheck != null)
        {
            throw new AlreadyFoundException("Passport number already exist");
        }
        Student student = studentRepo.findByEmail(passport.getEmail());
        if (student == null)
        {
            throw new NotFoundException("Student with this email is not found kindly enter valid email");
        }
        if (student.getPassport() != null)
        {
            throw new AlreadyFoundException("Passport for this student already found");
        }
        Passport passport1 = new Passport();
        passport1.setPassportNumber(passport.getPassportNumber());
        /*passport1.setDateOfExpiry(passport.getDateOfExpiry());*/
        passport1.setDateOfExpiry(new Date());
        /*passport1.setDateOfIssue(passport.getDateOfIssue());*/
        passport1.setDateOfIssue(new Date());
        passport1.setStudent(student);
        student.setPassport(passport1);
        passportRepo.save(passport1);
        return passport1;
    }
}
