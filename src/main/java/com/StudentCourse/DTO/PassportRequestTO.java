package com.StudentCourse.DTO;



import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
@Data
public class PassportRequestTO {
    @Pattern(regexp = "^[A-Z]{1}-[0-9]{7}$",message = "{passport.number.pattern}")
    @NotNull(message = "{passport.nu,ber.null}")
    @NotBlank(message = "{passport.number.blank}")
    @Column(nullable = false,unique = true)
    private String passportNumber;
    /*@DateTimeFormat(pattern = "yyyy-MM-dd")*/
/*    @NotNull(message = "Date of issue cannot be null")
    @NotBlank(message = "Date of issue cannot be left blank")*/
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    /*@JsonFormat(pattern = "yyyy-mm-dd",shape = JsonFormat.Shape.STRING)*/
    private LocalDate dateOfIssue;
    /*@DateTimeFormat(pattern = "yyyy-MM-dd")*/
/*    @NotNull(message = "Date of Expiry cannot be null")
    @NotBlank(message = "Date of Expiry cannot be left blank")*/
    @Column(nullable = false)
    /*@JsonFormat(pattern = "yyyy-mm-dd",shape = JsonFormat.Shape.STRING)*/
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfExpiry;
}
