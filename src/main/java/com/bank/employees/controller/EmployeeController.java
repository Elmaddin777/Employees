package com.bank.employees.controller;

import com.bank.employees.dto.EmployeeDto;
import com.bank.employees.dto.FullnamesDto;
import com.bank.employees.dto.StatusDto;
import com.bank.employees.entity.Employee;
import com.bank.employees.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping(value = "all")
    public ResponseEntity<List<EmployeeDto>> getEmployees(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "Id") String sortBy
    ){
            List<EmployeeDto> employees = employeeService.getEmployees(pageNo, pageSize, sortBy);
            return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable Long id){
        EmployeeDto employee = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping(value = "getFullNamesAsObjects")
    public ResponseEntity<List<FullnamesDto>> getFullnamesAsObjects(){
        List<FullnamesDto> fullnames = employeeService.getFullnamesObjects();
        return new ResponseEntity<>(fullnames, HttpStatus.OK);
    }

    @GetMapping(value = "getFullNamesAsList")
    public ResponseEntity<List<String>> getFullnamesAsList(){
        List<String> fullnames = employeeService.getFullNamesAsList();
        return new ResponseEntity<>(fullnames, HttpStatus.OK);
    }

    @PostMapping(value = "create", consumes={"application/json"})
    public ResponseEntity<EmployeeDto> create(@RequestBody Employee emp){
        EmployeeDto epmDto = employeeService.create(emp);
        return new ResponseEntity<>(epmDto, HttpStatus.OK);
    }

    @PutMapping(value = "update", consumes={"application/json"})
    public ResponseEntity<EmployeeDto> update(@RequestBody Employee emp){
        EmployeeDto epmDto = employeeService.update(emp);
        return new ResponseEntity<>(epmDto, HttpStatus.OK);
    }

    @PostMapping(value ="{empId}/department/{deptId}")
    public ResponseEntity<EmployeeDto> assignEmployeeToDepartment(
            @PathVariable long empId,
            @PathVariable long deptId){
        EmployeeDto epmDto = employeeService.assignEmployeeToDepartment(empId, deptId);
        if (epmDto == null)
            return  null;
        return new ResponseEntity<>(epmDto, HttpStatus.OK);
    }

    @PutMapping(value ="card/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatusDto> activateCard(@PathVariable Long empId){
        StatusDto statusDto =  employeeService.activateEmployeeCard(empId);
        return new ResponseEntity<>(statusDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "remove/{empId}")
    public void deleteEmployee(@PathVariable Long empId){
        employeeService.removeEmployee(empId);
    }

    @GetMapping(value = "department/{deptName}")
    public List<EmployeeDto> getEmployeesByDepartmentName(@PathVariable String deptName){
        List<EmployeeDto> emps = employeeService.getEmployeesByDepartment(deptName);
        return emps;
    }

    @GetMapping(value = "cardnumber/{cardNum}")
    public ResponseEntity<EmployeeDto> getEmployeeByCardNumber(@PathVariable Long cardNum){
        EmployeeDto employeeDto =  employeeService.getEmployeeByCardNumber(cardNum);
        return new ResponseEntity<EmployeeDto>(employeeDto, HttpStatus.OK);
    }


}
