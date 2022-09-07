package com.example.dz2_13.entity;


import java.util.Objects;

public class Employee {

    private final String firstName;
    private final String lastName;
    private final String patronymic;
    private final double salary;
    private final int departament;

    public Employee(String firstName, String lastName, String patronymic, double salary, int departament) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.salary = salary;
        this.departament = departament;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public double getSalary() {
        return salary;
    }

    public int getDepartament() {
        return departament;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        Employee c2 = (Employee) other;
        return lastName.equals(c2.lastName) && firstName.equals(c2.firstName) && patronymic.equals(c2.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, patronymic);
    }

    @Override
    public String toString() {
        return "Фамилия: " + lastName + ". Имя: " + firstName +". Отчество: " + patronymic;
    }

}
