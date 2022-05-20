package com.StudentCourse.DTO;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class PassportTO {
    @Pattern(regexp = "^(?!^0+$)[a-zA-Z0-9]{3,20}$")
    private String passportNumber;
    @Temporal(TemporalType.DATE)
    /*@Past*/
    private Date dateOfIssue;
    @Temporal(TemporalType.TIMESTAMP)
    /*@Future*/
    private Date dateOfExpiry;
    @Column(nullable = false)
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    private String email;
}
