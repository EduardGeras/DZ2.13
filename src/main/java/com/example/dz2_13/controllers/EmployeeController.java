package com.example.dz2_13.controllers;


import com.example.dz2_13.entity.Employee;
import com.example.dz2_13.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")

public class EmployeeController {
    @Autowired
    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")      // добавить сотрудника
    public Employee addEmployee(@RequestParam("lastName") String lastName,
                                @RequestParam("firstName") String firstName,
                                @RequestParam("patronymic") String patronymic,
                                @RequestParam("salary") double salary,
                                @RequestParam("departament") int departament) {
        return employeeService.add(firstName, lastName, patronymic, salary, departament);
    }
    @GetMapping("/delete")    // удалить сотрудника
    public Employee deleteEmployee(@RequestParam("lastName") String lastName,
                                   @RequestParam("firstName") String firstName,
                                   @RequestParam("patronymic") String patronymic) {
        return employeeService.delete(lastName, firstName, patronymic);
    }
    @GetMapping("/find")      // проверить на наличие сотрудника
    public Employee findEmployee(@RequestParam("lastName") String lastName,
                                 @RequestParam("firstName") String firstName,
                                 @RequestParam("patronymic") String patronymic) {
        return employeeService.find(lastName, firstName, patronymic);
    }
    @GetMapping ("/all")     // список сотрудников
    public List<Employee> getAll(){
        return employeeService.getAll();
    }

}

