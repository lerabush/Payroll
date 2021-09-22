package com.example.payroll.exceptions;
import java.lang.RuntimeException;
public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(Long id){
        super("Could not find employee with id= "+id);
    }
}
