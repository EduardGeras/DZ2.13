package com.example.dz2_13.service.impl;


import com.example.dz2_13.entity.Employee;
import com.example.dz2_13.exceptions.EmployeeAlreadyAddedException;
import com.example.dz2_13.exceptions.EmployeeException;
import com.example.dz2_13.exceptions.EmployeeNotFoundException;
import com.example.dz2_13.exceptions.EmployeeStorageIsFullException;
import com.example.dz2_13.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final int limit = 10;
    public  Map<String, Employee> employees = new HashMap<>();
    public Map<String, Employee> getEmployees() {
        return employees;
    }
    @Override
    public Employee add(String firstName, String lastName, String patronymic, double salary, int department) {
        if (StringUtils.isAlpha(firstName) && StringUtils.isAlpha(lastName) && StringUtils.isAlpha(patronymic)) {
            Employee employee = new Employee(StringUtils.capitalize(firstName),
                    StringUtils.capitalize(lastName),
                    StringUtils.capitalize(patronymic),
                    salary, department);
            String key = getKey(firstName, lastName, patronymic);
            if (employees.containsKey(key)) {
                throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует");
            }
            if (employees.size() < limit) {
                employees.put(key, employee);
                return employee;
            }
            throw new EmployeeStorageIsFullException("Штат сотрудников полный. Кого увольняем?)");
        }
        throw new EmployeeException();
    }
    @Override
    public Employee delete(String firstName, String lastName, String patronymic) {
        String key = getKey(firstName, lastName, patronymic);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Такого сотрудника нет в организации");
        }
        return employees.remove(key);
    }
    @Override
    public Employee find(String firstName, String lastName, String patronymic) {
        String key = getKey(firstName, lastName, patronymic);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Такого сотрудника нет в организации");
        }
        return employees.get(key);
    }
    public List<Employee> getAll() {
        return new ArrayList<>(employees.values());
    }
    private String getKey(String firstName, String lastName, String patronymic) {
        return firstName + " " + lastName + " " + patronymic;
    }
}
