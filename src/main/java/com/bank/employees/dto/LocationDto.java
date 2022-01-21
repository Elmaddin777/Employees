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
public class LocationDto {

    @JsonIgnore
    private Long Id;

    private String branch;

    private String city;

    @JsonIgnore
    private Set<DepartmentDto> departments = new HashSet<>();

}
