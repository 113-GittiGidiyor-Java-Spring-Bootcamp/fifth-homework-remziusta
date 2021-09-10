package com.sms.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseSlimDTO {
    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty(value = "NESNE YONELIK PROGRAMLAMA - 1")
    @NotBlank(message = "Course name is mandatory.")
    private String courseName;
}
