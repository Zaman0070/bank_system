package com.xmlprasing;

public record Employee(String id, String name, String role, String salary, String age, String phone, String address) {

    // Override toString() for easy printing
    @Override
    public String toString() {
        return "Employee ID: " + id + "\n" +
                "Name: " + name + "\n" +
                "Role: " + role + "\n" +
                "Salary: $" + salary + "\n" +
                "Age: " + age + "\n" +
                "Phone: " + phone + "\n" +
                "Address: " + address;
    }
}
