package com.sms.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstructorPutSalaryDTO {

    @NotNull (message = "Id is mandatory.")
    private Long ─▒nstructorId;

    @NotNull(message = "Percent is mandatory.")
    private Double percent;
}
