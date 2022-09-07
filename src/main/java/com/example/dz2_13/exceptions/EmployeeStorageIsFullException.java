package com.example.dz2_13.exceptions;

public class EmployeeStorageIsFullException extends RuntimeException {
    public EmployeeStorageIsFullException(String s) {
        super(s);
    }
}
