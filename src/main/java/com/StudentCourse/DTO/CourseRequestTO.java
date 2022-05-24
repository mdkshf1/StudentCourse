package com.StudentCourse.DTO;


import com.StudentCourse.entities.Course;
import lombok.Data;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CourseRequestTO {
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
    @Column(nullable = false)
    private Integer duration;

    public static Course mapper(CourseRequestTO request){
        Course course = new Course();
        course.setName(request.getName());
        course.setAmount(request.getAmount());
        course.setDescription(request.getDescription());
        course.setDuration(request.getDuration());
        return course;
    }
}
