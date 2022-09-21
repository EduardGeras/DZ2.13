package com.example.dz2_13.service;

import com.example.dz2_13.entity.Employee;

import java.util.List;

public interface DepartamentService {

    Employee maxSalary(int departament);

    Employee minSalary(int departament);
    List <Employee> departmentEmployees(int departament);
    List <Employee> allEmployees();

    Object getAll();

    Object getEmployees();
}
