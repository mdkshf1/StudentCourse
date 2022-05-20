package com.StudentCourse.DTO;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class StudentTO {
    @Size(min = 3,message = "Name should be valid")
    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false)
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    private String email;
    @Column(nullable = false)
    private String password;
}
