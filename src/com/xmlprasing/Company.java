package com.xmlprasing;

import java.util.List;

public class Company {

    public static void main(String[] args) {
        EmployeeParser parser = new EmployeeXMLParser();
        List<Employee> employees = parser.parseEmployees(XmlFilePath.EMPLOYEE_XML_FILE);
        employees.forEach(System.out::println);
    }
}
