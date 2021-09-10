package com.sms.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentGetDTO {

    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty(value = "Remzi")
    @NotBlank(message = "Name is mandatory.")
    private String name;

    @ApiModelProperty(value = "25")
    @NotNull(message = "Age is mandatory.")
    private LocalDate birthDate;

    @ApiModelProperty(value = "HATAY/ISKENDERUN")
    @NotBlank(message = "Address is mandatory.")
    private String address;

    @ApiModelProperty(value = "E or K")
    @NotNull(message = "Address is mandatory.")
    private char gender;

    private Set<CourseSlimDTO> courses;

}
