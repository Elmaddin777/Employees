package com.bank.employees.mapper;

import com.bank.employees.dto.EmployeeDto;
import com.bank.employees.entity.Employee;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeMapper {
    public static Employee toEmployee(EmployeeDto empDto){
        if (empDto == null)
            return  null;

        return Employee.builder()
                .id(empDto.getId())
                .department_id(empDto.getDepartment_id())
                .card_id(empDto.getCard_id())
                .lastName(empDto.getLastName())
                .firstName(empDto.getFirstName())
                .email(empDto.getEmail())
                .salary(empDto.getSalary())
                .department(DepartmentMapper.toDepartment(empDto.getDepartment()))
                .emp_card(CardMapper.toCard(empDto.getEmp_card()))
                .build();
    }

    public static EmployeeDto toEmployeeDto(Employee employee){
        if (employee == null)
            return null;

        return EmployeeDto.builder()
                .id(employee.getId())
                .department_id(employee.getDepartment_id())
                .card_id(employee.getCard_id())
                .lastName(employee.getLastName())
                .firstName(employee.getFirstName())
                .email(employee.getEmail())
                .salary(employee.getSalary())
                .department(DepartmentMapper.toDepartmentDto(employee.getDepartment()))
                .emp_card(CardMapper.toCardDto(employee.getEmp_card()))
                .build();
    }

    public static Employee toUpdateUser(Employee emp, Employee updateEmp){
        emp.setDepartment_id(updateEmp.getDepartment_id() == null ? emp.getDepartment_id() : updateEmp.getDepartment_id());
        emp.setLastName(updateEmp.getLastName() == null ? emp.getLastName() : updateEmp.getLastName());
        emp.setFirstName(updateEmp.getFirstName() == null ? emp.getFirstName() : updateEmp.getFirstName());
        emp.setSalary(updateEmp.getSalary() == null ? emp.getSalary() : updateEmp.getSalary());
        emp.setDepartment(updateEmp.getDepartment() == null ? emp.getDepartment() : updateEmp.getDepartment());
        emp.setEmp_card(updateEmp.getEmp_card() == null ? emp.getEmp_card() : updateEmp.getEmp_card());

        return emp;
    }

    public static List<EmployeeDto> toEmployeeDtoList(List<Employee> emps){
        if (emps == null)
            return null;

        return emps
                .stream()
                .map(employee -> toEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    public static List<Employee> toEmployeeList(List<EmployeeDto> empDtos){
        if (empDtos == null)
            return null;

        return empDtos
                .stream()
                .map(EmployeeMapper::toEmployee)
                .collect(Collectors.toList());
    }



}
