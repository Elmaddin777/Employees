package com.bank.employees.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CardDto {

    @JsonIgnore
    private Long Id;

    private Long card_number;

    private Boolean active;

    @JsonIgnore
    private Long employee_id;

    private Date issue_date;

    private Date end_date;

    @JsonIgnore
    EmployeeDto employee;
}
