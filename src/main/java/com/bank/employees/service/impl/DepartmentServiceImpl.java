package com.bank.employees.service.impl;

import com.bank.employees.entity.Department;
import com.bank.employees.repository.DepartmentRepository;
import com.bank.employees.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository deptRepo;

    @Override
    public List<Department> getAllDepartments() {
        return deptRepo.findAll();
    }
}
