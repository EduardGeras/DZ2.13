package com.example.dz2_13;

import com.example.dz2_13.entity.Employee;
import com.example.dz2_13.exceptions.EmployeeNotFoundException;
import com.example.dz2_13.service.impl.DepartamentServiceImpl;
import com.example.dz2_13.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartamentServiceTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartamentServiceImpl departamentService;

    @BeforeEach
    public void beforeEach() {
        List<Employee> employees = List.of(
                new Employee("Иван", "Иванов", "Иванович", 55_000, 1),
                new Employee("Петр", "Петров", "Петрович", 50_000, 1),
                new Employee("Роман", "Романов", "Романович", 56_000, 2),
                new Employee("Олег", "Олегов", "Олегович", 54_000, 2),
                new Employee("Борис", "Борисов", "Борисович", 52_000, 2)
        );
        when(departamentService.getAll()).thenReturn(employees);
    }

    @ParameterizedTest
    @MethodSource("maxSalaryParams")
    public void maxSalaryPositiveTest(int departament, Employee expected) {
        assertThat(departamentService.maxSalary(departament)).isEqualTo(expected);
    }

    @Test
    public void maxSalaryNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departamentService.maxSalary(5));
    }

    @ParameterizedTest
    @MethodSource("minSalaryParams")
    public void minSalaryPositiveTest(int departament, Employee expected) {
        assertThat(departamentService.minSalary(departament)).isEqualTo(expected);
    }

    @Test
    public void minSalaryNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departamentService.minSalary(5));
    }
    
    @ParameterizedTest
    @MethodSource("departmentEmployees")
    public void departmentEmployeesPositiveTest(int departament, List<Employee> expected) {
//        assertThat(departamentService.departmentEmployees(departament)).containsExactlyElementsOf(expected);
    }

    public static Stream<Arguments> maxSalaryParams() {
        return Stream.of(
                Arguments.of(1, new Employee("Иван", "Иванов", "Иванович", 55_000, 1)),
                Arguments.of(2, new Employee("Роман", "Романов", "Романович", 56_000, 2))
        );
    }

    public static Stream<Arguments> minSalaryParams() {
        return Stream.of(
                Arguments.of(1, new Employee("Петр", "Петров", "Петрович", 50_000, 1)),
                Arguments.of(2, new Employee("Борис", "Борисов", "Борисович", 52_000, 2))
        );
    }
}
