package com.bank.employees.service.impl;

import com.bank.employees.dto.EmployeeDto;
import com.bank.employees.dto.FullnamesDto;
import com.bank.employees.dto.StatusDto;
import com.bank.employees.entity.Card;
import com.bank.employees.entity.Department;
import com.bank.employees.entity.Employee;
import com.bank.employees.exception.DepartmentNotFoundException;
import com.bank.employees.exception.EmpToDeptAssignException;
import com.bank.employees.exception.EmployeeNotFoundException;
import com.bank.employees.exception.EmployeesNotFoundException;
import com.bank.employees.mapper.EmployeeMapper;
import com.bank.employees.mapper.FullnamesMapper;
import com.bank.employees.repository.CardRepository;
import com.bank.employees.repository.DepartmentRepository;
import com.bank.employees.repository.EmployeeRepository;
import com.bank.employees.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepo;
    private final CardRepository cardRepo;
    private final DepartmentRepository deptRepo;

    @Override
    public List<EmployeeDto> getEmployees(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC ,sortBy));
        Page<Employee> pagedResult = employeeRepo.findAll(paging);
        if (pagedResult.hasContent())
            return EmployeeMapper.toEmployeeDtoList(pagedResult.getContent());
        throw new EmployeesNotFoundException("Can't get employees");
    }


    public EmployeeDto findEmployeeById(Long id) {
        Employee emp = employeeRepo
                .findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        return EmployeeMapper.toEmployeeDto(emp);
    }

    @Override
    public List<FullnamesDto> getFullnamesObjects() {
        List<Employee> emps = employeeRepo.findAll();
        return FullnamesMapper.toFullnamesDtoList(emps);
    }

    @Override
    public List<String> getFullNamesAsList() {
        List<String> fullNames = new ArrayList<>();
        employeeRepo
                .findAll()
                .stream()
                .forEach(emp -> fullNames.add(emp.getFirstName() + " " + emp.getLastName()));

        return fullNames;
    }

    @Override
    public EmployeeDto create(Employee employee) {
            if (deptRepo.findById(employee.getDepartment_id()).isEmpty())
                throw new DepartmentNotFoundException("Department not found");
            Employee emp = employeeRepo.save(employee);
            Long empId = emp.getId();
            Long deptId = emp.getDepartment_id();
            if (deptId != null)
                emp = EmployeeMapper.toEmployee(assignEmployeeToDepartment(empId, deptId));
            assignNewCardToEmployee(emp);
            return EmployeeMapper.toEmployeeDto(employeeRepo.save(emp));
    }

    @Override
    public EmployeeDto update(Employee employee){
        if (deptRepo.findById(employee.getDepartment_id()).isEmpty())
            throw new DepartmentNotFoundException("Department not found");
        if (employee.getId() != null){
            Optional<Employee> emp = employeeRepo.findById(employee.getId());
            if (emp.isEmpty())
                throw new EmployeeNotFoundException("Employee not found");
            Employee saveEmployee = emp.get();
            Long empId = saveEmployee.getId();
            Long deptId = employee.getDepartment_id();
            if (deptId != null){
                EmployeeDto ell = assignEmployeeToDepartment(empId, deptId);
                saveEmployee = EmployeeMapper.toEmployee(ell);
            }
            saveEmployee =  EmployeeMapper.toUpdateUser(saveEmployee, employee);
            return EmployeeMapper.toEmployeeDto(employeeRepo.save(saveEmployee));
        }
        throw new EmployeeNotFoundException("Employee not found");
    }

    @Override
    public EmployeeDto assignEmployeeToDepartment(Long empId, Long deptId){
       Optional<Department> optDepartment = deptRepo.findById(deptId);
       Optional<Employee> optEmployee = employeeRepo.findById(empId);
       Department department = null;
       Employee employee = null;
       if (optDepartment.isPresent() && optEmployee.isPresent()){
           employee = optEmployee.get();
           employee.setDepartment_id(deptId);
       }else {
           throw new EmpToDeptAssignException("Assignment is not successfull. Employee or Department couldn't be found");
       }
       return EmployeeMapper.toEmployeeDto(employeeRepo.save(employee));
    }

    private void assignNewCardToEmployee(Employee employee){
        Card card = new Card();
        card.setCardNumber(generateRnadomCardNumber());
        card.setEmployeeId(employee.getId());
        card.setIssue_date(new Date());
        card.setActive(false);
        Card savedCard = cardRepo.save(card);
        employee.setCard_id(savedCard.getId());
    }

    private Long generateRnadomCardNumber(){
        int min = 100000;
        int max = 999999;
        Random rand = new Random();
        int randomNum = min + rand.nextInt(max);
        return (long) randomNum;
    }

    @Override
    public StatusDto activateEmployeeCard(Long empId) {
        Optional<Employee> optEmployee = employeeRepo.findById(empId);
        if (optEmployee.isEmpty())
            return new StatusDto(false);
        Card card = cardRepo.getCardByEmployeeId(empId);
        if (card != null){
            card.setActive(true);
            cardRepo.save(card);
            return new StatusDto(true);
        }
        return new StatusDto(false);
    }

    @Override
    public void removeEmployee(Long id) {
        Employee emp =  employeeRepo
                .findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        employeeRepo.delete(emp);
    }

    @Override
    public List<EmployeeDto> getEmployeesByDepartment(String departmentName) {
        departmentName = departmentName.toLowerCase();
        boolean deptExists = deptRepo.existsDepartmentByDeptName(departmentName);
        if(! deptExists)
            throw new DepartmentNotFoundException("Department not found");
        List<Employee> emps = employeeRepo.getEmployeesByDepartmentName(departmentName);
        if(emps.isEmpty())
            throw new EmployeesNotFoundException("Can't get employees");

        return EmployeeMapper.toEmployeeDtoList(emps);
    }

    @Override
    public EmployeeDto getEmployeeByCardNumber(Long cardNum) {
        Employee employee = employeeRepo.getEmployeesByCardNumber(cardNum);
        if (employee == null)
            throw new EmployeeNotFoundException("Employee not found");
        return EmployeeMapper.toEmployeeDto(employee);
    }



}
