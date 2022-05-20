package com.StudentCourse.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@IdClass(ReviewPK.class)
@Data
public class Review {
    @Id
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Student student;
    @Id
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Course course;
    @Min(0)
    @Max(5)
    private Integer rating;
    private String comment;
}
