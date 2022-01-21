package com.bank.employees.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class EmployeeDto {

    @JsonIgnore
    private Long id;

    @JsonIgnore
    private Long department_id;

    @JsonIgnore
    private Long card_id;

    private String lastName;

    private String firstName;

    private String email;

    private BigDecimal salary;

    DepartmentDto department;

    CardDto emp_card;
}
