package com.example.dz2_13;

import com.example.dz2_13.entity.Employee;
import com.example.dz2_13.exceptions.EmployeeAlreadyAddedException;
import com.example.dz2_13.exceptions.EmployeeException;
import com.example.dz2_13.exceptions.EmployeeNotFoundException;
import com.example.dz2_13.exceptions.EmployeeStorageIsFullException;
import com.example.dz2_13.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class EmployeeServiceTest {

    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    @ParameterizedTest
    @MethodSource("params")
    public void addNegativeTest1(String firstName, String lastName, String patronymic, double salary, int department) {
        Employee employee = new Employee(firstName, lastName, patronymic, salary, department);
        assertThat(employeeService.add(firstName, lastName, patronymic, salary, department));

        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.add(firstName, lastName, patronymic, salary, department));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void addNegativeTest2(String firstName, String lastName, String patronymic, double salary, int department) {
        List<Employee> employees = generateEmployee(10);
            employees.forEach(employee ->
                    assertThat(employeeService.add(
                            employee.getFirstName(),
                            employee.getLastName(),
                            employee.getPatronymic(),
                            employee.getSalary(),
                            employee.getDepartament()))
                            .isEqualTo(employee)
            );

        assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(() -> employeeService.add(firstName, lastName, patronymic, salary, department));
    }

    @Test
    public void addNegativeTest3() {
        assertThatExceptionOfType(EmployeeException.class)
                .isThrownBy(() -> employeeService.add("Иван=", "Иванов", "Иванович", 55_000, 1));
        assertThatExceptionOfType(EmployeeException.class)
                .isThrownBy(() -> employeeService.add("Иван", ")Иванов", "Иванович", 55_000, 1));
        assertThatExceptionOfType(EmployeeException.class)
                .isThrownBy(() -> employeeService.add("Иван", ")Иванов", null, 55_000, 1));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void deleteNegativeTest(String firstName, String lastName, String patronymic, double salary, int department) {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.delete("aaa", "Sss", "ppp"));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void deletePositiveTest(String firstName, String lastName, String patronymic, double salary, int department) {
        Employee employee = new Employee(firstName, lastName, patronymic, salary, department);
        assertThat(employeeService.add(firstName, lastName, patronymic, salary, department))
                .isEqualTo(employee);
        assertThat(employeeService.delete(firstName, lastName, patronymic))
                .isEqualTo(employee);
        assertThat(employeeService.getAll())
                .isEmpty();
    }

    private static List<Employee> generateEmployee(int size) {
         return  Stream.iterate(1, i -> i + 1)
                    .limit(size)
                    .map(i -> new Employee(
                            "Firstname" + (char) ((int) 'a' + i),
                            "Lastname" + (char) ((int) 'a' + i),
                            "Patronymic" + (char) ((int) 'a' + i),
                            50_000 + i,
                            i))
                    .collect(Collectors.toList());
        }

    public static Stream<Arguments> params() {
        return Stream.of(
                Arguments.of("Иван", "Иванов", "Иванович", 55_000, 1),
                Arguments.of("Петр", "Петров", "Петрович", 50_000, 1),
                Arguments.of("Роман", "Романов", "Романович", 56_000, 2),
                Arguments.of("Олег", "Олегов", "Олегович", 54_000, 2),
                Arguments.of("Борис", "Борисов", "Борисович", 52_000, 2)
        );
    }

}
