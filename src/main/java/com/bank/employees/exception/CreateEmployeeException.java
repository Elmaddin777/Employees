package com.bank.employees.exception;

public class CreateEmployeeException extends RuntimeException{
    public CreateEmployeeException(String message) {
        super(message);
    }
}
