package com.bank.employees.repository;

import com.bank.employees.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    boolean existsDepartmentByDeptName (String deptName);

}
