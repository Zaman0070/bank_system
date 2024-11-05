package com.csvhandling;
import java.util.List;

public interface EmployeeCSV {
    void saveEmployees(List<Employee> employees, String filePath);
    List<Employee> loadEmployees(String filePath);
}
