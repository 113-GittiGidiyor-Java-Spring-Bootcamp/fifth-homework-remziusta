package com.sms.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseGetDTO {

    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty(example = "NESNE YONELIK PROGRAMLAMA - 1")
    @NotBlank(message = "Course name is mandatory.")
    private String courseName;

    @ApiModelProperty(example = "5")
    @NotNull(message = "Course credit is mandatory.")
    @Min(value = 1, message = "Credit must be greater than 1")
    @Max(value = 9, message = "Credit must be less than 9")
    private Integer courseCredit;

    @ApiModelProperty(example = "NYP - 1")
    @NotBlank(message = "Course code is mandatory.")
    private String courseCode;

    private Set<StudentSlimDTO> students;

    private long instructor_id;
}
