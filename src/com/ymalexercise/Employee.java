package com.ymalexercise;

import java.util.List;

public class Employee {
    private String id;
    private String name;
    private String role;
    private double salary;
    private int age;
    private List<String> skills;

    // Constructors, Getters, and Setters
    public Employee() {}

    public Employee(String id, String name, String role, double salary, int age, List<String> skills) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.salary = salary;
        this.age = age;
        this.skills = skills;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", skills=" + skills +
                '}';
    }
}
