package com.example.dz2_13.exceptions;

public class EmployeeAlreadyAddedException extends RuntimeException {
    public EmployeeAlreadyAddedException(String s) {
        super(s);
    }
}
