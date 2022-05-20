package com.StudentCourse.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ReviewPK implements Serializable {
    private Student student;
    private Course course;
}
