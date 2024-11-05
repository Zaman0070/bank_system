package com.csvhandling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Company {

    public static void main(String[] args) {
        String filePath = "employees.csv";
        EmployeeCSV employeeService = new CsvEmployeeService();

        // Load employees from CSV file
        List<Employee> employees = employeeService.loadEmployees(filePath);
        if (employees != null && !employees.isEmpty()) {
            employees.forEach(System.out::println);
        } else {
            employees = new ArrayList<>();
            System.out.println("No employees loaded, starting with an empty list.");
        }

        // Create and add a new employee
        Employee newEmployee = new Employee(
                "E109", "John Doe", "Backend Developer", 78000.0, 30,
                Arrays.asList("Java", "Spring", "SQL")
        );

        // Add to the list
        employees.add(newEmployee);

        // Save updated list of employees to CSV
        employeeService.saveEmployees(employees, filePath);
    }
}
