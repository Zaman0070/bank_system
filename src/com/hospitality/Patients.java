package com.hospitality;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Patients {

    private Connection connection;
    private Scanner scanner;

    public Patients(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addPatient() {
        System.out.println("Enter patient name: ");
        String name = scanner.nextLine();
        System.out.println("Enter patient Gender: ");
        String gender = scanner.nextLine();
        System.out.println("Enter patient age: ");
        int age = scanner.nextInt();

        try {
            String query = "INSERT INTO patients (name,gender,age) VALUES (?,?,?)";
            var statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, gender);
            statement.setInt(3, age);
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Patient added successfully");
            } else {
                System.out.println("Failed to add patient");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listPatients() {
        String query = "SELECT * FROM patients";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            var result = statement.executeQuery();
            System.out.println("Patients list:");
            System.out.println("+----+-----------------+------------+------------+");
            System.out.println("| ID | Name            | Age        | Gender     |");
            System.out.println("+----+-----------------+------------+------------+");
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                int age = result.getInt("age");
                String gender = result.getString("gender");
                System.out.printf("| %-2d | %-15s | %-10d | %-10s |\n", id, name, age, gender);
                System.out.println("+----+-----------------+------------+------------+");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean getPatientById(int id) {
        String query = "SELECT * FROM patients WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            var result = statement.executeQuery();
            if (result.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
