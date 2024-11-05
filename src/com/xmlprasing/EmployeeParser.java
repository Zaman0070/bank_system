package com.xmlprasing;
import java.util.List;

@FunctionalInterface
public interface EmployeeParser {
    List<Employee> parseEmployees(String filePath);
}
