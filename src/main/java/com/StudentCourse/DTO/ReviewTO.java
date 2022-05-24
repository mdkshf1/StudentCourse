package com.StudentCourse.DTO;


import com.StudentCourse.entities.Review;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class ReviewTO {
    private Long courseId;
    private Long StudentId;
    @Min(value = 0,message = "{rating.minimum}")
    @Max(value = 5,message = "{rating.maximum}")
    private Integer rating;
    private String comment;
    public static ReviewTO mapper(Review review){
        ReviewTO reviewTO = new ReviewTO();
        reviewTO.setComment(review.getComment());
        reviewTO.setRating(review.getRating());
        reviewTO.setCourseId(review.getCourse().getId());
        reviewTO.setStudentId(review.getStudent().getId());
        return reviewTO;
    }
}
