package com.StudentCourse.Repositories;

import com.StudentCourse.entities.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepository extends JpaRepository<Passport,Long> {
    Passport findPassportByPassportNumber(String passportNumber);
}
