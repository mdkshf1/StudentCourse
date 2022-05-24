package com.StudentCourse.entities;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Slf4j
@Entity
public class Course extends AuditingInfo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 5,max = 20,message = "{course.name.size}")
    @NotBlank(message = "{course.name.blank}")
    @NotNull(message = "{course.name.null}")
    @Column(nullable = false)
    private String name;
    @NotBlank(message = "{course.amount.blank}")
    @NotNull(message = "{course.amount.null}")
    @Column(nullable = false)
    private String amount;
    @NotBlank(message = "{course.description.blank}")
    @NotNull(message = "{course.description.null}")
    @Column(nullable = false)
    private String description;
    /*    @NotBlank(message = "Duration of Course cannot be Blank")
        @NotNull(message = "Duration of Course cannot be Null")*/
    @Column(nullable = false)
    private Integer duration;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "courses")
    private List<Student> students;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "course")
    private List<Review> reviews;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount='" + amount + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                '}';
    }
}
