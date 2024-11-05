package com.ymalexercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Company {

    public static void main(String[] args) {
        String filePath = "employees.yaml";
        EmployeeService employeeService = new YamlEmployeeService();
        List<Employee> employees = employeeService.loadEmployees(filePath);
        if (employees == null) {
            employees = new ArrayList<>();
        } else {
            employees.forEach(System.out::println);
        }

        Employee newEmployee = new Employee(
                "E111", "John Lee", "Backend Developer", 78000.0, 32,
                Arrays.asList("Java", "Spring", "PostgreSQL", "Flutter")
        );

        updateEmployeeList(employees, newEmployee);

        // Save the updated list of employees
        employeeService.saveEmployees(employees, filePath);

        // Reload employees in a new thread after saving
        Thread reloadThread = new Thread(() -> {
            List<Employee> reloadedEmployees = employeeService.loadEmployees(filePath);
            System.out.println("\nReloaded Employees from YAML:");
            if (reloadedEmployees != null) {
                reloadedEmployees.forEach(System.out::println);
            }
        });
        reloadThread.start();
    }

    // Method to add or update employee in the list
    private static void updateEmployeeList(List<Employee> employees, Employee newEmployee) {
        // Find existing employee by ID
        Optional<Employee> existingEmployeeOpt = employees.stream()
                .filter(emp -> emp.getId().equals(newEmployee.getId()))
                .findFirst();

        if (existingEmployeeOpt.isPresent()) {
            // Update existing employee details
            Employee existingEmployee = existingEmployeeOpt.get();
            existingEmployee.setName(newEmployee.getName());
            existingEmployee.setRole(newEmployee.getRole());
            existingEmployee.setSalary(newEmployee.getSalary());
            existingEmployee.setAge(newEmployee.getAge());
            existingEmployee.setSkills(newEmployee.getSkills());
            System.out.println("Updated existing employee with ID: " + newEmployee.getId());
        } else {
            // Add new employee if ID does not exist
            employees.add(newEmployee);
            System.out.println("Added new employee with ID: " + newEmployee.getId());
        }
    }
}
