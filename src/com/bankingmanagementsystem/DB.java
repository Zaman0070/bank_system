package com.bankingmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static Connection connection = null;
    private static final String url = "jdbc:mysql://localhost:3306/banking_system";
    private static final String username = "root";
    private static final String password = "Saman@12345";
    public  DB() {}

    // Method to establish a database connection
    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load driver explicitly
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }

        return connection;
    }

    // Method to close the database connection if it exists
    public static void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Error while closing the connection: " + e.getMessage());
            } finally {
                connection = null;
            }
        }
    }
}
