package com.example.dz2_13.service;

import com.example.dz2_13.entity.Employee;

public interface EmployeeService {

    Employee add(String firstName, String lastName, String patronymic, double salary, int department);

    Employee delete(String firstName, String lastName, String patronymic);
    Employee find(String firstName, String lastName, String patronymic);

    Object getAll();
}
