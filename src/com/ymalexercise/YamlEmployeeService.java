package com.ymalexercise;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.LoaderOptions;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.IOException;
import java.util.List;
import org.yaml.snakeyaml.TypeDescription;

public class YamlEmployeeService implements EmployeeService {

    @Override
    public void saveEmployees(List<Employee> employees, String filePath) {
        EmployeeList employeeList = new EmployeeList();
        employeeList.setEmployees(employees);

        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setIndent(2);
        dumperOptions.setPrettyFlow(true);
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        Yaml yaml = new Yaml(dumperOptions);
        try (FileWriter writer = new FileWriter(filePath)) {
            yaml.dump(employeeList, writer);
            System.out.println("Employees saved to YAML successfully.");
        } catch (IOException e) {
            System.err.println("Failed to save employees to YAML: " + e.getMessage());
        }

    }


    @Override
    public List<Employee> loadEmployees(String filePath) {
        LoaderOptions loaderOptions = new LoaderOptions();
        loaderOptions.setAllowDuplicateKeys(false);  // Customize options as needed
        loaderOptions.setTagInspector(tag -> true);  // Allow all tags

        // Register EmployeeList as a recognized type
        Constructor constructor = new Constructor(EmployeeList.class, loaderOptions);
        TypeDescription employeeListDesc = new TypeDescription(EmployeeList.class);
        employeeListDesc.putListPropertyType("employees", Employee.class);
        constructor.addTypeDescription(employeeListDesc);

        Yaml yaml = new Yaml(constructor);

        try (InputStream inputStream = new FileInputStream(filePath)) {
            EmployeeList employeeList = yaml.load(inputStream);
            System.out.println("Employees loaded from YAML successfully.");
            return employeeList.getEmployees();
        } catch (IOException e) {
            System.err.println("Failed to load employees from YAML: " + e.getMessage());
            return null;
        }
    }

}



