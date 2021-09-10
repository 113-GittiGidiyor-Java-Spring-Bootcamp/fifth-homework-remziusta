package com.sms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class InstructorTransactionLogger {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long instructorId;
    private String clientIpAddress;
    private String clientUrl;
    private String sessionActivityId;
    private LocalDate transactionTime;
    private Double percentageRate;
    private Double salaryBefore;
    private Double salaryAfter;
}
