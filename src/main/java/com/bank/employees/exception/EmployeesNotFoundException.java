package com.bank.employees.exception;

public class EmployeesNotFoundException extends RuntimeException{
    public EmployeesNotFoundException(String message) {
        super(message);
    }
}
