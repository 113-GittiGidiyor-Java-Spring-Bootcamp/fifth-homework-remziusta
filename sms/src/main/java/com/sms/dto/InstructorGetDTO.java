package com.sms.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstructorGetDTO {

    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty(value = "CAGLAR OFLAZOGLU")
    @NotBlank(message = "Name is mandatory.")
    private String name;

    @ApiModelProperty(value = "HATAY/ANTAKYA")
    @NotBlank(message = "Address is mandatory.")
    private String address;

    @ApiModelProperty(value = "5555555555")
    @NotBlank(message = "Phone number is mandatory.")
    @Size(min = 10, max = 10,message = "Phone number must be 10 characters")
    private String phoneNumber;

    @ApiModelProperty(value = "1000.0")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @NotNull(message = "Salary is mandatory.")
    private Double salary;

    private Set<CourseSlimDTO> courses;
}
