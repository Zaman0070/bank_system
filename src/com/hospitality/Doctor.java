package com.hospitality;

import java.sql.Connection;
import java.sql.SQLException;

public class Doctor {
    private Connection connection;

    public Doctor(Connection connection) {
        this.connection = connection;
    }


//    public void addDoctor() {
//        System.out.println("Enter doctor name: ");
//        String name = scanner.nextLine();
//        System.out.println("Enter doctor specialization: ");
//        String specialization = scanner.nextLine();
//
//        try {
//            String query = "INSERT INTO doctors (name,specialization) VALUES (?,?)";
//            var statement = connection.prepareStatement(query);
//            statement.setString(1, name);
//            statement.setString(2, specialization);
//            int rows = statement.executeUpdate();
//            if (rows > 0) {
//                System.out.println("Doctor added successfully");
//            } else {
//                System.out.println("Failed to add doctor");
//            }
//        }catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void doctorList() {
        String query = "SELECT * FROM doctors";
        try {
            var statement = connection.prepareStatement(query);
            var result = statement.executeQuery();
            System.out.println("Doctors list:");
            System.out.println("+---- +-----------------+-----------------+");
            System.out.println("| ID  | Name            | Specialization  |");
            System.out.println("+---- +-----------------+-----------------+");
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String specialization = result.getString("specialization");
                System.out.printf("| %3d | %-15s | %-15s |\n", id, name, specialization);
                System.out.println("+---- +-----------------+-----------------+");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getDoctorById(int id) {
        String query = "SELECT * FROM doctors WHERE id = ?";
        try {
            var statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            var result = statement.executeQuery();
            if (result.next()) {
                String name = result.getString("name");
                String specialization = result.getString("specialization");
                System.out.println("Doctor details:");
                System.out.println("+---- +-----------------+-----------------+");
                System.out.println("| ID  | Name            | Specialization  |");
                System.out.println("+---- +-----------------+-----------------+");
                System.out.printf("| %2d | %-15s | %-15s |\n", id, name, specialization);
                System.out.println("+---- +-----------------+-----------------+");
                return true;
            } else {
                System.out.println("Doctor not found");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
