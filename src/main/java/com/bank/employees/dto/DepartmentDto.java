package com.bank.employees.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class DepartmentDto {

    @JsonIgnore
    private Long Id;

    @JsonIgnore
    private Long location_id;

    private String deptName;

    @JsonIgnore
    private Set<EmployeeDto> employees = new HashSet<>();

    private LocationDto location;
}
