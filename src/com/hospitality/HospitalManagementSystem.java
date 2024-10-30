package com.hospitality;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class HospitalManagementSystem {
    public static void main(String[] args) {

        Connection connection = DB.getConnection();
        Scanner scanner = new Scanner(System.in);
        Patients patients = new Patients(connection, scanner);
        Doctor doctor = new Doctor(connection);

        while (true) {
            System.out.println("HOSPITAL MANAGEMENT SYSTEM");
            System.out.println("1. Add Patient");
            System.out.println("2. List Patients");
            System.out.println("3. List Doctor");
            System.out.println("4. Book Appointment");
            System.out.println("5. Exit");
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    patients.addPatient();
                    break;
                case 2:
                    patients.listPatients();
                    break;
                case 3:
                    doctor.doctorList();
                    break;
                case 4:
                    bookAppointment(connection, scanner, patients, doctor);
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }


        }

    }

    public static void bookAppointment(Connection connection, Scanner scanner, Patients patients, Doctor doctor) {
        System.out.println("Enter patient id: ");
        int patientId = scanner.nextInt();
        System.out.println("Enter doctor id: ");
        int doctorId = scanner.nextInt();
        scanner.nextLine(); // Clear the newline left by nextInt()

        System.out.println("Enter appointment date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        if (patients.getPatientById(patientId) && doctor.getDoctorById(doctorId)) {
            if (isAvailableDoctor(doctorId, date, connection)) {
                String query = "INSERT INTO appointments (patient_id, doctor_id, appointment_date) VALUES (?, ?, ?)";
                try {
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setInt(1, patientId);
                    statement.setInt(2, doctorId);
                    statement.setString(3, date);
                    int rows = statement.executeUpdate();
                    if (rows > 0) {
                        System.out.println("Appointment booked successfully");
                    } else {
                        System.out.println("Failed to book appointment");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Doctor not available on this date");
            }
        } else {
            System.out.println("Either doctor or patient doesn't exist!");
        }
    }


    public static boolean isAvailableDoctor(int doctorId, String date,Connection connection) {
        String query = "SELECT * FROM appointments WHERE doctor_id = ? AND appointment_date = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, doctorId);
            statement.setString(2, date);
            var result = statement.executeQuery();
            return !result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
         return false;
    }
}