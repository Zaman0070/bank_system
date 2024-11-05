package com.ymalexercise;
import java.util.List;


public interface EmployeeService {
     void saveEmployees(List<Employee> employees, String filePath);
     List<Employee> loadEmployees(String filePath);
}
