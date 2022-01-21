package com.bank.employees.repository;

import com.bank.employees.dto.EmployeeDto;
import com.bank.employees.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "SELECT e FROM Employee e ORDER BY e.id DESC")
    List<Employee> findAllByOrderByIdDesc();

    @Query(value = "SELECT e FROM Employee e WHERE e.department.deptName = :deptName")
    List<Employee> getEmployeesByDepartmentName(@Param("deptName") String deptName);

    @Query(value = "SELECT e FROM Employee e WHERE e.emp_card.cardNumber = :cardNum")
    Employee getEmployeesByCardNumber(@Param("cardNum") Long cardNum);
}
