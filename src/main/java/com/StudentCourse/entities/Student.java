package com.StudentCourse.entities;

import jdk.dynalink.linker.LinkerServices;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@Slf4j
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 3,message = "Name should be valid")
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    private String email;
    @Column(nullable = false)
    private String password;
    @ManyToMany(mappedBy = "student",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Course> courses;
    @OneToOne(mappedBy = "student",fetch = FetchType.EAGER,orphanRemoval = true)
    private Passport passport;
    @OneToMany(mappedBy = "student",orphanRemoval = true)
    private List<Review> review;
}
