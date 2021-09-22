package com.example.payroll.exceptions;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(Long id){
        super("Could not find employee with id= "+id);
    }
}