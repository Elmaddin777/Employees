package com.bank.employees.service;

import com.bank.employees.dto.EmployeeDto;
import com.bank.employees.dto.FullnamesDto;
import com.bank.employees.dto.StatusDto;
import com.bank.employees.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> getEmployees(Integer pageNo, Integer pageSize, String sortBy);

    List<EmployeeDto> getEmployeesByDepartment(String departmentName);

    EmployeeDto findEmployeeById(Long id);

    List<FullnamesDto> getFullnamesObjects();

    List<String> getFullNamesAsList();

    EmployeeDto create(Employee emp);

    EmployeeDto update(Employee emp);

    void removeEmployee(Long id);

    EmployeeDto assignEmployeeToDepartment(Long employee_id, Long department_id);

    StatusDto activateEmployeeCard(Long employee_id);

    EmployeeDto getEmployeeByCardNumber(Long card_id);
}
