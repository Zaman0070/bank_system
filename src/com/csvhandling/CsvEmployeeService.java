package com.csvhandling;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvEmployeeService implements EmployeeCSV {

    @Override
    public void saveEmployees(List<Employee> employees, String filePath) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Write header
            writer.writeNext(new String[] { "ID", "Name", "Role", "Salary", "Age", "Skills" });

            // Write employee data
            for (Employee employee : employees) {
                String skills = String.join(";", employee.getSkills());
                writer.writeNext(new String[] {
                        employee.getId(),
                        employee.getName(),
                        employee.getRole(),
                        String.valueOf(employee.getSalary()),
                        String.valueOf(employee.getAge()),
                        skills
                });
            }

            System.out.println("Employees saved to CSV successfully.");
        } catch (IOException e) {
            System.err.println("Failed to save employees to CSV: " + e.getMessage());
        }
    }

    @Override
    public List<Employee> loadEmployees(String filePath) {
        List<Employee> employees = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            reader.readNext(); // Skip header

            while ((line = reader.readNext()) != null) {
                String id = line[0];
                String name = line[1];
                String role = line[2];
                double salary = Double.parseDouble(line[3]);
                int age = Integer.parseInt(line[4]);
                List<String> skills = Arrays.asList(line[5].split(";"));

                Employee employee = new Employee(id, name, role, salary, age, skills);
                employees.add(employee);
            }

            System.out.println("Employees loaded from CSV successfully.");
        } catch (IOException e) {
            System.err.println("Failed to load employees from CSV: " + e.getMessage());
        }

        return employees;
    }
}
