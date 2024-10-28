package com.bankingmanagementsystem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
    private Connection connection;
    private Scanner scanner;

    public User(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addUser() {
        System.out.println("Enter user name: ");
        String name = scanner.nextLine();
        System.out.println("Enter user email: ");
        String email = scanner.nextLine();
        System.out.println("Enter user password: ");
        String password = scanner.nextLine();
        if (user_exists(email)) {
            System.out.println("User already exists");
            return;
        }
        String query = "INSERT INTO users (name,email,password) VALUES (?,?,?)";
        try {
            var statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("User added successfully");
            } else {
                System.out.println("Failed to add user");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public String loginUser(){
        System.out.println("Enter user email: ");
        String email = scanner.nextLine();
        System.out.println("Enter user password: ");
        String password = scanner.nextLine();
        if (user_exists(email)) {
            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            try {
                var statement = connection.prepareStatement(query);
                statement.setString(1, email);
                statement.setString(2, password);
                var result = statement.executeQuery();
                if (result.next()) {
                    System.out.println("Login successful");
                    return email;
                } else {
                    System.out.println("Invalid email or password");
                    return null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("User does not exist");
        }
        return null;
    }




    public boolean user_exists(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        try {
            var statement = connection.prepareStatement(query);
            statement.setString(1, email);
            var result = statement.executeQuery();
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
