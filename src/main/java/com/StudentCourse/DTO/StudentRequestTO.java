package com.StudentCourse.DTO;


import com.StudentCourse.entities.Student;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Data
@Slf4j
public class StudentRequestTO {
    @Size(min = 3,message = "{student.name.size}")
    @NotBlank(message = "{student.name.blank}")
    @NotNull(message = "{student.name.null}")
    @Column(nullable = false)
    private String name;
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",message = "{student.email.format}")
    @NotBlank(message = "{student.email.blank}")
    @NotNull(message = "{student.email.null}")
    @Column(nullable = false)
    private String email;
    @Size(min = 6,message = "{student.password.length")
    @NotNull(message = "{student.password.blank}")
    @NotBlank(message = "{student.password.null}")
    @Column(nullable = false)
    private String password;

    public static StudentRequestTO mapper(Student student){
        StudentRequestTO request = new StudentRequestTO();
        request.setEmail(student.getEmail());
        request.setName(student.getName());
        request.setPassword(student.getPassword());
        return request;
    }
}
