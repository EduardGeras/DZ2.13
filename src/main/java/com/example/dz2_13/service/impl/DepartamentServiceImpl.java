package com.example.dz2_13.service.impl;

import com.example.dz2_13.entity.Employee;
import com.example.dz2_13.exceptions.EmployeeNotFoundException;
import com.example.dz2_13.service.DepartamentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartamentServiceImpl implements DepartamentService {
    private final EmployeeServiceImpl employeeService;

    public DepartamentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee maxSalary(int departament) {
        return employeeService.getEmployees()
                .values()
                .stream()
                .filter(e -> e.getDepartament() == departament)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException(""));
    }
    @Override
    public Employee minSalary(int departament) {
        return employeeService.getEmployees()
                .values()
                .stream()
                .filter(e -> e.getDepartament() == departament)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException(""));
    }
    @Override
    public List <Employee> departmentEmployees(int departament) {
        return employeeService.getEmployees()
                .values()
                .stream()
                .filter(e -> e.getDepartament() == departament)
                .collect(Collectors.toList());
    }
    public List <Employee> allEmployees(){
        return new ArrayList<>(employeeService.getEmployees().values());
    }
}

