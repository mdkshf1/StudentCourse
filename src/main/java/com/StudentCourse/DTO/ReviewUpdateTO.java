package com.StudentCourse.DTO;


import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class ReviewUpdateTO {
    private String comment;
    @Min(value = 0,message = "{rating.minimum}")
    @Max(value = 5,message = "{rating.maximum}")
    private Integer rating;
}
