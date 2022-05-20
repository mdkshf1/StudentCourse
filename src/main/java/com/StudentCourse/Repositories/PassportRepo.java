package com.StudentCourse.Repositories;

import com.StudentCourse.entities.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepo extends JpaRepository<Passport,Long> {
    Passport findByPassportNumber(String number);
}
