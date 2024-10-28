package com.bankingmanagementsystem;
import java.sql.*;
import java.util.*;


public class AccountManager {
    private final Connection connection;
    private final Scanner scanner;

    public AccountManager(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }


    public void creditMoney(long account_no) {
        System.out.println("Enter amount to credit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter your pin: ");
        String pin = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            if (account_no != 0) {
                PreparedStatement statement = connection.prepareStatement(Queries.SELECT_BALANCE_BY_NO_AND_PIN);
                statement.setLong(1, account_no);
                statement.setString(2, pin);
                ResultSet result = statement.executeQuery();

                if (result.next()) {
                    PreparedStatement creditStatement = connection.prepareStatement(Queries.CREDIT_AMOUNT);
                    creditStatement.setDouble(1, amount);
                    creditStatement.setLong(2, account_no);
                    int rows = creditStatement.executeUpdate();

                    if (rows > 0) {
                        System.out.println("Rs." + amount + " credited successfully");
                        connection.commit();
                    } else {
                        System.out.println("Failed to credit money");
                        connection.rollback();
                    }
                } else {
                    System.out.println("Invalid Security Pin!");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException rollbackException) {
                System.out.println(rollbackException.getMessage());
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public void debit_amount(long account_no){
        System.out.println("Enter amount to debit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter your pin: ");
        int pin = scanner.nextInt();
        try {
            connection.setAutoCommit(false);
            if (account_no!=0){
                PreparedStatement statement = connection.prepareStatement(Queries.SELECT_BALANCE_BY_NO_AND_PIN);
                statement.setLong(1,account_no);
                statement.setInt(2,pin);
                ResultSet result = statement.executeQuery();

                if(result.next()){
                    double balance = result.getDouble("balance");
                    if(balance>=amount){
                        PreparedStatement debitStatement = connection.prepareStatement(Queries.DEBIT_AMOUNT);
                        debitStatement.setDouble(1,amount);
                        debitStatement.setLong(2,account_no);
                        int rows = debitStatement.executeUpdate();
                        if(rows>0){
                            System.out.println("Rs."+amount+" debited successfully");
                            connection.commit();
                            connection.setAutoCommit(true);
                        }else {
                            System.out.println("Failed to debit money");
                            connection.rollback();
                            connection.setAutoCommit(true);
                        }
                    }else {
                        System.out.println("Insufficient balance!");
                    }
                }else {
                    System.out.println("Invalid Security Pin!");
                }
            }

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }



    public void transferMoney(long sender_account_no)throws SQLException{
        System.out.println("Enter account number to transfer: ");
        long transferAccount = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter your pin: ");
        int pin = scanner.nextInt();
        try {
            connection.setAutoCommit(false);
            if (sender_account_no!=0 && transferAccount!=0){
                PreparedStatement statement = connection.prepareStatement(Queries.SELECT_BALANCE_BY_NO_AND_PIN);
                statement.setLong(1,sender_account_no);
                statement.setInt(2,pin);
                ResultSet result = statement.executeQuery();

                if(result.next()){
                    double balance = result.getDouble("balance");
                    if(balance>=amount){
                        PreparedStatement debitStatement = connection.prepareStatement(Queries.DEBIT_AMOUNT);
                        debitStatement.setDouble(1,amount);
                        debitStatement.setLong(2,sender_account_no);
                        int debitRows = debitStatement.executeUpdate();
                        if(debitRows>0){
                            PreparedStatement creditStatement = connection.prepareStatement(Queries.CREDIT_AMOUNT);
                            creditStatement.setDouble(1,amount);
                            creditStatement.setLong(2,transferAccount);
                            int creditRows = creditStatement.executeUpdate();
                            if(creditRows>0){
                                System.out.println("Rs."+amount+" transferred successfully to account number "+transferAccount);
                                connection.commit();
                                connection.setAutoCommit(true);
                            }else {
                                System.out.println("Failed to transfer money");
                                connection.rollback();
                                connection.setAutoCommit(true);
                            }
                        }else {
                            System.out.println("Failed to debit money");
                            connection.rollback();
                            connection.setAutoCommit(true);
                        }
                    }else {
                        System.out.println("Insufficient balance!");
                    }
                }else {
                    System.out.println("Invalid Security Pin!");
                }
            }

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }




    public void getBalance(long account_no){
        System.out.println("Enter your pin: ");
        int pin = scanner.nextInt();
        try {
            PreparedStatement statement = connection.prepareStatement(Queries.SELECT_BALANCE_BY_NO_AND_PIN);
            statement.setLong(1,account_no);
            statement.setInt(2,pin);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                double balance = result.getDouble("balance");
                System.out.println("Your account balance is Rs."+balance);
            }else {
                System.out.println("Invalid Security Pin!");
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }




}
