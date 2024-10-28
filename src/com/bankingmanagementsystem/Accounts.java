package com.bankingmanagementsystem;

import java.sql.Connection;
import java.util.Scanner;

public class Accounts {
    private Connection connection;
    private Scanner scanner;

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
                throw new RuntimeException("Account already exists");
            }
        }



        String query = "INSERT INTO accounts (account_no, name, email, balance, pin) VALUES (?, ?, ?, ?, ?)";

        try {
            long account_no = generateAccountNo();
            var statement = connection.prepareStatement(query);
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
            e.printStackTrace();
        }
        throw new RuntimeException("Failed to open account");
    }




    public long getAccountNo(String email) {
        String query = "SELECT account_no FROM accounts WHERE email = ?";
        try {
            var statement = connection.prepareStatement(query);
            statement.setString(1, email);
            var result = statement.executeQuery();
            if (result.next()) {
                return result.getLong("account_no");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


public long generateAccountNo(){
    String query = "SELECT MAX(account_no) FROM accounts";
    try {
        var statement = connection.prepareStatement(query);
        var result = statement.executeQuery();
        if (result.next()) {
            return result.getLong(1) + 1;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 1000000000;
}

    public boolean accountExists(long account_no) {
        String query = "SELECT * FROM accounts WHERE account_no = ?";
        try {
            var statement = connection.prepareStatement(query);
            statement.setLong(1, account_no);
            var result = statement.executeQuery();
            return result.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
