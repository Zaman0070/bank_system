package com.bankingmanagementsystem;
import java.util.*;
import java.sql.*;


public class BankingSystem {
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        try {
            Connection connection = DB.getConnection();
            Scanner input = new Scanner(System.in);
            User user = new User(connection, input);
            AccountManager accountManager = new AccountManager(connection, input);
            Accounts accounts = new Accounts(connection, input);

            String email;
            long account_no;

            while (true){
                System.out.println("*** BANKING MANAGEMENT SYSTEM ***");
                System.out.println();
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.println();
                System.out.println("Enter your choice: ");
                int choice = scanner.nextInt();
                switch (choice){
                    case 1:
                        user.addUser();
                        break;
                    case 2:
                        email = user.loginUser();
                        if (email!=null) {
                            while (true) {
                                System.out.println();
                                System.out.println("1. Open Account");
                                System.out.println("2. Main Menu");
                                System.out.println("3. Exit");
                                System.out.println();
                                System.out.println("Enter your choice: ");
                                int option = scanner.nextInt();
                                switch (option) {
                                    case 1:
                                        account_no = accounts.openAccount(email);
                                        while (true) {
                                            System.out.println();
                                            System.out.println("1. Credit Money");
                                            System.out.println("2. Debit Money");
                                            System.out.println("3. Check Balance");
                                            System.out.println("4. Transfer Money");
                                            System.out.println("5. Exit");
                                            System.out.println();
                                            System.out.println("Enter your choice: ");
                                            int opt = scanner.nextInt();
                                            switch (opt) {
                                                case 1:
                                                    accountManager.creditMoney(account_no);
                                                    break;
                                                case 2:
                                                    accountManager.debit_amount(account_no);
                                                    break;
                                                case 3:
                                                    accountManager.getBalance(account_no);
                                                    break;
                                                case 4:
                                                    accountManager.transferMoney(account_no);
                                                    break;
                                                case 5:
                                                    System.exit(0);
                                                    break;
                                                default:
                                                    System.out.println("Invalid choice");
                                            }
                                        }
                                    case 2:
                                        account_no = accounts.getAccountNo(email);
                                        while (true) {
                                            System.out.println();
                                            System.out.println("1. Credit Money");
                                            System.out.println("2. Debit Money");
                                            System.out.println("3. Check Balance");
                                            System.out.println("4. Transfer Money");
                                            System.out.println("5. Exit");
                                            System.out.println();
                                            System.out.println("Enter your choice: ");
                                            int opt = scanner.nextInt();
                                            switch (opt) {
                                                case 1:
                                                    accountManager.creditMoney(account_no);
                                                    break;
                                                case 2:
                                                    accountManager.debit_amount(account_no);
                                                    break;
                                                case 3:
                                                    accountManager.getBalance(account_no);
                                                    break;
                                                case 4:
                                                    accountManager.transferMoney(account_no);
                                                    break;
                                                case 5:
                                                    System.exit(0);
                                                    break;
                                                default:
                                                    System.out.println("Invalid choice");
                                            }
                                        }
                                    case 3:
                                        System.exit(0);
                                        break;
                                }
                            }
                        }
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice");
                }

            }

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}