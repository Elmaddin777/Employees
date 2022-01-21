package com.bank.employees.mapper;

import com.bank.employees.dto.FullnamesDto;
import com.bank.employees.entity.Employee;
import java.util.List;
import java.util.stream.Collectors;

public class FullnamesMapper {

    public static List<FullnamesDto> toFullnamesDtoList(List<Employee> emps) {
        if (emps.isEmpty())
            return null;

        return emps
                .stream()
                .map(emp -> toFullnamesDto(emp))
                .collect(Collectors.toList());
    }

    private static FullnamesDto toFullnamesDto(Employee emp) {
        if (emp == null)
            return null;

        return FullnamesDto.builder()
                .firstName(emp.getFirstName())
                .lastName(emp.getLastName())
                .build();
    }
}