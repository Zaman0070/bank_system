package com.bankingmanagementsystem;

import java.sql.Connection;
import java.util.Scanner;

public class Accounts {
    private final Connection connection;
    private final Scanner scanner;

    public Accounts(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }


    public long openAccount(String email) {
        System.out.println("Enter account holder name: ");
        String name = scanner.nextLine();
        System.out.println("Enter account initial balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Set account pin: ");
        String pin = scanner.nextLine();

        long existingAccountNo = getAccountNo(email);
        if(existingAccountNo != 0){
            if (accountExists(existingAccountNo)) {
                System.out.println("Account already exists");
                return existingAccountNo;
            }
        }




        try {
            long account_no = generateAccountNo();
            var statement = connection.prepareStatement(Queries.ACCOUNT_OPEN);
            statement.setLong(1, account_no);
            statement.setString(2, name);
            statement.setString(3, email);
            statement.setDouble(4, balance);
            statement.setString(5, pin);
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Account opened successfully");
                return account_no;
            } else {
                throw new RuntimeException("Failed to open account");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        throw new RuntimeException("Failed to open account");
    }




    public long getAccountNo(String email) {
        try {
            var statement = connection.prepareStatement(Queries.ACCOUNT_BY_EMAIL);
            statement.setString(1, email);
            var result = statement.executeQuery();
            if (result.next()) {
                return result.getLong("account_no");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }


    public long generateAccountNo() {
        try {
            // Ensure the SQL query returns the maximum account number, or 999999999 if none exist
            var statement = connection.prepareStatement(Queries.GENERATE_ACCOUNT_NO);
            var result = statement.executeQuery();

            // If an account number exists, increment it by 1, otherwise start from 1000000000
            if (result.next()) {
                long currentMaxAccountNo = result.getLong(1);
                return Math.max(currentMaxAccountNo + 1, 1000000000);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Default starting account number if the query fails or no account number is present
        return 1000000000;
    }


    public boolean accountExists(long account_no) {

        try {
            var statement = connection.prepareStatement(Queries.ACCOUNT_BY_NO);
            statement.setLong(1, account_no);
            var result = statement.executeQuery();
            return result.next();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
