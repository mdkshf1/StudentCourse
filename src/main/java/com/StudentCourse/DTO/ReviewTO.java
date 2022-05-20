package com.StudentCourse.DTO;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class ReviewTO {
    private String email;
    private String courseName;
    @Min(0)
    @Max(5)
    private Integer rating;
    private String comment;
}
