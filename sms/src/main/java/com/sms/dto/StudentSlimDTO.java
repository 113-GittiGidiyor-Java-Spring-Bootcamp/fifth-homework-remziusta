package com.sms.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentSlimDTO {
    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty(example = "Remzi")
    @NotBlank(message = "Name is mandatory.")
    private String name;

    @ApiModelProperty(example = "25")
    @NotNull(message = "Age is mandatory.")
    private LocalDate birthDate;
}
