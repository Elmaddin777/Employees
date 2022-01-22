package com.bank.employees.controller;

import com.bank.employees.entity.Department;
import com.bank.employees.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/departments")
public class DepartmentController {
    private final DepartmentService deptService;

    @GetMapping(value = "")
    public ResponseEntity<List<Department>> getDepartments(){
        List<Department> departments = deptService.getAllDepartments();
        return new ResponseEntity<List<Department>>(departments, HttpStatus.OK);
    }
}
